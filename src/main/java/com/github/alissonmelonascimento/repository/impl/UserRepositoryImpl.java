package com.github.alissonmelonascimento.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.github.alissonmelonascimento.model.User;
import com.github.alissonmelonascimento.repository.UserRepository;

import io.agroal.api.AgroalDataSource;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {
	
	@Inject
	AgroalDataSource datasource;	

	@Override
	public List<User> getUsers() {
		
		String sql = " select * from public.usuario order by nome ";
		List<User> users = new ArrayList<>();
		
		PreparedStatement stm = null;
		ResultSet rs = null;
        try{
        	Connection conn = datasource.getConnection();
            stm = conn.prepareStatement(sql);
            rs = stm.executeQuery();
            while(rs.next()) {
            	User user = new User();
            	user.setAnoNascimento(rs.getInt("ano"));
            	user.setNome(rs.getString("nome"));
            	user.setSobreNome(rs.getString("sobrenome"));
            	
            	users.add(user);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
            	if(stm != null){
            		stm.close();
            	}
            	
            	if(rs != null) {
            		rs.close();
            	}
			} catch (SQLException e) {
				e.printStackTrace();
			}
        }		
		
        return users;
	}

}
