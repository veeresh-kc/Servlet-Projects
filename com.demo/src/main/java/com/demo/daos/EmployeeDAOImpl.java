package com.demo.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.demo.model.Employee;

public class EmployeeDAOImpl implements EmployeeDAO {
	@Override
	public List<Employee> findAll() {
		String sql = "select * from employees order by id desc";
		List<Employee> elist = new ArrayList<>();
		try {
			Connection con = MyDBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				elist.add(mapROW(rs));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return elist;
	}

	@Override
	public Optional<Employee> findById(Integer id) {
		String sql = "select * from employees where id=?";
		try {
			Connection con = MyDBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Employee e = new Employee();
				e.setId(rs.getInt("id"));
				e.setName(rs.getString("name"));
				e.setEmail(rs.getString("email"));
				e.setSal(rs.getDouble("sal"));
				e.setDept(rs.getString("dept"));
				Timestamp ts = rs.getTimestamp("createdAt");
				e.setCreatedAt(ts != null ? ts.toLocalDateTime() : LocalDateTime.now());
				return Optional.of(e);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);

		}
		return Optional.empty();

	}

	private Employee mapROW(ResultSet rs) throws SQLException {
		Employee e = new Employee();
		e.setId(rs.getInt("id"));
		e.setName(rs.getString("name"));
		e.setEmail(rs.getString("email"));
		Double sal = rs.getDouble("sal");
		sal = sal != null ? sal : 0.0;
		e.setSal(sal);
		e.setDept(rs.getString("dept"));
		LocalDateTime createdAt = null;
		Timestamp ts = rs.getTimestamp("createdAt");
		createdAt = ts != null ? ts.toLocalDateTime() : LocalDateTime.now();
		e.setCreatedAt(createdAt);
		return e;
	}

	@Override
	public int insert(Employee e) {
		String sql = "INSERT INTO employees (name, email, sal, dept) VALUES (?, ?, ?, ?)";
		int i = 0;
		try {
			Connection con = MyDBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, e.getName());
			ps.setString(2, e.getEmail());
			ps.setDouble(3, e.getSal());
			ps.setString(4, e.getDept());
			i = ps.executeUpdate();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return i;
	}

	@Override
	public int update(Employee e) {
		String sql = "update employees set name=?,email=?,sal=?,dept=? where id=?";
		int i = 0;
		try {
			Connection con = MyDBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, e.getName());
			ps.setString(2, e.getEmail());
			ps.setDouble(3, e.getSal());
			ps.setString(4, e.getDept());
			ps.setInt(5, e.getId());
			i=ps.executeUpdate();
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
		return i;
	}

	@Override
	public int delete(Integer id) {
		String sql = "delete from employees where id=?";
		int i = 0;
		try {
			Connection con = MyDBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			i = ps.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return i;
	}

}
