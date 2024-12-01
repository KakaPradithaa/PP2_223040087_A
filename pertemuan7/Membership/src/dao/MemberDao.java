/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pertemuan7.Membership.src.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;
import org.apache.ibatis.session.SqlSessionFactory;
import pertemuan7.Membership.src.db.MySqlConnection;
import pertemuan7.Membership.src.model.JenisMember;
import pertemuan7.Membership.src.model.Member;

public class MemberDao {

    public MemberDao(SqlSessionFactory sqlSessionFactory) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public MemberDao() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public int insert(Member member) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO member (id, nama, jenis_member_id) VALUES (?, ?, ?)");
            statement.setString(1, member.getId());
            statement.setString(2, member.getNama());
            statement.setString(3, member.getJenisMember().getId());
            
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int update(Member member) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("UPDATE member SET nama = ?, jenis_member_id = ? WHERE id = ?");
            statement.setString(1, member.getNama());
            statement.setString(2, member.getJenisMember().getId());
            statement.setString(3, member.getId());
            
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public int delete(Member member) {
        int result = -1;
        try (Connection connection = MySqlConnection.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM member WHERE id = ?");
            statement.setString(1, member.getId());
            
            result = statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Member> findAll() {
        List<Member> list = new ArrayList<>();
        try (Connection connection = MySqlConnection.getInstance().getConnection();
             Statement statement = connection.createStatement()) {
            
            try (ResultSet resultSet = statement.executeQuery(
                "SELECT member.id, member.nama, jenis_member.id AS jenis_member_id, jenis_member.nama AS jenis_member_nama " +
                "FROM member JOIN jenis_member ON jenis_member.id = member.jenis_member_id")) {
                
                while (resultSet.next()) {
                    Member member = new Member();
                    member.setId(resultSet.getString("id"));
                    member.setNama(resultSet.getString("nama"));
                    
                    JenisMember jenisMember = new JenisMember();
                    jenisMember.setId(resultSet.getString("jenis_member_id"));
                    jenisMember.setNama(resultSet.getString("jenis_member_nama"));
                    
                    member.setJenisMember(jenisMember);
                    
                    list.add(member);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Member> findA11() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
