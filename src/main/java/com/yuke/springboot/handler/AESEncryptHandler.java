package com.yuke.springboot.handler;

import com.yuke.springboot.util.AesEncryptUtil;
import lombok.SneakyThrows;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AESEncryptHandler extends BaseTypeHandler {

    @SneakyThrows
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, AesEncryptUtil.encrypt((String) parameter));
    }

    @SneakyThrows
    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String columnValue = rs.getString(columnName);
        return AesEncryptUtil.desEncrypt(columnValue);
    }

    @SneakyThrows
    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String columnValue = rs.getString(columnIndex);
        return AesEncryptUtil.desEncrypt(columnValue);
    }

    @SneakyThrows
    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        String columnValue = cs.getString(columnIndex);
        return AesEncryptUtil.desEncrypt(columnValue);
    }
}
