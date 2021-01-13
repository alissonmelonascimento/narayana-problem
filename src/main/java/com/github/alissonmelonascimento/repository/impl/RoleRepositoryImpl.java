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

import com.github.alissonmelonascimento.model.Role;
import com.github.alissonmelonascimento.repository.RoleRepository;

import io.agroal.api.AgroalDataSource;

@Traced
@ApplicationScoped
public class RoleRepositoryImpl implements RoleRepository {
	
	@Inject
	AgroalDataSource datasource;	

	@Override
	public void insert(Role role) {

		PreparedStatement stm = null;
		Connection conn = null;
		
		try {
        	conn = datasource.getConnection();
            stm = conn.prepareStatement("insert into public.role(id) values(?)");
            stm.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
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
	public List<Role> findAll() {
		List<Role> roles = new ArrayList<>();
		
		PreparedStatement stm = null;
		ResultSet rs = null;
		Connection conn = null;
        try{
        	conn = datasource.getConnection();
            stm = conn.prepareStatement("select * from public.role order by id");
            rs = stm.executeQuery();
            
            if(!rs.next()) {
            	return null;
            }
            
            Role role = new Role();
            role.setId(rs.getString("id"));
            roles.add(role);
            
            while(rs.next()) {
            	role = new Role();
                role.setId(rs.getString("id"));
                roles.add(role);            	
            }
        }catch(Exception e){
            e.printStackTrace();
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
		
        return roles;
	}

}
