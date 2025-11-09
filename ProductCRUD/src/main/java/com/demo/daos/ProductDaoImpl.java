package com.demo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.demo.models.Product;

public class ProductDaoImpl implements ProductDaos{

	@Override
	public List<Product> findAll() {
		String sql="select productId, productName,avalQuantity,price,expirydate from products order by id desc";
		List<Product>prodList=new ArrayList<>();
		try (Connection con=DBUtil.getConnection();
				PreparedStatement ps=con.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();){
			while(rs.next()) {
				prodList.add(bindProduct(rs));
			}	
		}catch (SQLException e) {
			throw new RuntimeException("Error from featching a findAll",e);
		}
		return prodList;
	}

	private Product bindProduct(ResultSet rs) throws SQLException {
		 Product product = new Product();
		    product.setProductId(rs.getInt("productId"));
		    product.setProductName(rs.getString("productName"));
		    product.setAvalQuantity(rs.getInt("avalQuantity"));
		    product.setPrice(rs.getDouble("price"));
		    product.setExpiryDate(rs.getTimestamp("expiryDate").toLocalDateTime().toLocalDate());
		    return product;
	}

	@Override
	public Optional<Product> getById(Integer id) {
	    String sql = "SELECT productId, productName, avalQuantity, price, expiryDate FROM products WHERE productId = ?";
	    
	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, id);
	        try (ResultSet rs = ps.executeQuery()) {
	            if (rs.next()) {
	                Product product = bindProduct(rs);
	                return Optional.of(product);
	            }
	        }
	    } catch (SQLException e) {
	        throw new RuntimeException("Error fetching product by ID", e);
	    }
	    return Optional.empty(); 
	}

	@Override
	public int insert(Product p) {
	    String sql = "INSERT INTO products (productName, avalQuantity, price, expiryDate) VALUES (?, ?, ?, ?)";
	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
	        ps.setString(1, p.getProductName());
	        ps.setInt(2, p.getAvalQuantity());
	        ps.setDouble(3, p.getPrice());
	        ps.setTimestamp(4, Timestamp.valueOf(p.getExpiryDate().atStartOfDay())); 
	        try (ResultSet keys=ps.getGeneratedKeys()){
	        	if(keys!=null && keys.next()) {
	        		p.setProductId(keys.getInt(1));
	        	}	
	        }
	        return ps.executeUpdate();
	    } 
	    catch (SQLException e) {
	        throw new RuntimeException("Error inserting product", e);
	    }
	}
	
	@Override
	public int update(Product p) {
	    String sql = "UPDATE products SET productName = ?, avalQuantity = ?, price = ?, expiryDate = ? WHERE productId = ?";
	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {     
	        ps.setString(1, p.getProductName());
	        ps.setInt(2, p.getAvalQuantity());
	        ps.setDouble(3, p.getPrice());
	        ps.setTimestamp(4, Timestamp.valueOf(p.getExpiryDate().atStartOfDay())); 
	        ps.setInt(5, p.getProductId());
	        return ps.executeUpdate(); 
	    } catch (SQLException e) {
	        throw new RuntimeException("Error updating product", e);
	    }
	}
	@Override
	public int delete(Integer id) {
	    String sql = "DELETE FROM products WHERE productId = ?";
	    try (Connection con = DBUtil.getConnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, id);
	        return ps.executeUpdate();
	    } catch (SQLException e) {
	        throw new RuntimeException("Error deleting product", e);
	    }
	}

}
