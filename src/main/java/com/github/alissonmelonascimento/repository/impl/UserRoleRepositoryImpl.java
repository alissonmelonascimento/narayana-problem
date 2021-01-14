package com.github.alissonmelonascimento.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.eclipse.microprofile.opentracing.Traced;

import com.github.alissonmelonascimento.model.UserRole;
import com.github.alissonmelonascimento.repository.UserRoleRepository;

import io.agroal.api.AgroalDataSource;

@Traced
@ApplicationScoped
public class UserRoleRepositoryImpl implements UserRoleRepository {
	
	@Inject
	AgroalDataSource datasource;	

	@Override
	public void insert(UserRole userRole) {
		PreparedStatement stm = null;
		Connection conn       = null;
		
		try {
        	conn = datasource.getConnection();
            stm = conn.prepareStatement("insert into public.user_role(user_id, role_id) values(?,?)");
            stm.setInt(1, userRole.getUserId());
            stm.setString(2, userRole.getRoleId());
            stm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            try {
            	if(stm != null && !stm.isClosed()){
            		stm.close();
            	}
            	
            	if(conn != null && !conn.isClosed()){
            		conn.close();
            	}            	
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }
	}

	@Override
	public List<UserRole> findByUsuario(Integer userId) {
		List<UserRole> userRoles = new ArrayList<>();
		
		PreparedStatement stm = null;
		ResultSet rs          = null;
		Connection conn       = null;
        try{
        	conn = datasource.getConnection();
            stm = conn.prepareStatement("select * from public.user_role where user_id = ? order by role_id");
            stm.setInt(1, userId);
            rs = stm.executeQuery();
            
            if(!rs.next()) {
            	return null;
            }
            
            UserRole ur = new UserRole();
            ur.setRoleId(rs.getString("role_id"));
            ur.setUserId(rs.getInt("user_id"));
            userRoles.add(ur);
            
            while(rs.next()) {
            	ur = new UserRole();
                ur.setRoleId(rs.getString("role_id"));
                ur.setUserId(rs.getInt("user_id"));
                userRoles.add(ur);            	
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            try {
            	if(rs != null && !rs.isClosed()) {
            		rs.close();
            	}            	
            	
            	if(stm != null && !stm.isClosed()){
            		stm.close();
            	}
            	
            	if(conn != null && !conn.isClosed()){
            		conn.close();
            	}            	
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }	
		
        return userRoles;
	}

	@Override
	public void deleteAll() {

		PreparedStatement stm = null;
		Connection conn       = null;
		
		try {
        	conn = datasource.getConnection();
            stm = conn.prepareStatement("delete from public.user_role");
            stm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            try {
            	if(stm != null && !stm.isClosed()){
            		stm.close();
            	}
            	
            	if(conn != null && !conn.isClosed()){
            		conn.close();
            	}            	
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }		
		
	}
	

	@Override
	public List<UserRole> getAllCadastrados() {
		List<UserRole> userRoles = new ArrayList<>();
		
		PreparedStatement stm = null;
		ResultSet rs          = null;
		Connection conn       = null;
        try{
        	conn = datasource.getConnection();
            stm = conn.prepareStatement("select * from public.user_role order by user_id, role_id");
            rs = stm.executeQuery();
            
            if(!rs.next()) {
            	return null;
            }
            
            UserRole ur = new UserRole();
            ur.setRoleId(rs.getString("role_id"));
            ur.setUserId(rs.getInt("user_id"));
            userRoles.add(ur);
            
            while(rs.next()) {
            	ur = new UserRole();
                ur.setRoleId(rs.getString("role_id"));
                ur.setUserId(rs.getInt("user_id"));
                userRoles.add(ur);            	
            }
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally{
            try {
            	if(rs != null && !rs.isClosed()) {
            		rs.close();
            	}            	
            	
            	if(stm != null && !stm.isClosed()){
            		stm.close();
            	}
            	
            	if(conn != null && !conn.isClosed()){
            		conn.close();
            	}            	
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }	
		
        return userRoles;
	}

	
	
}
