package sophist.common.channel;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class FileDetailIdGenerator implements IdentifierGenerator  {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
	    
		String prefix = "FD";
	    Connection connection = session.connection();

	    try {
	        Statement statement=connection.createStatement();


	        ResultSet rs=statement.executeQuery("select case when char_length(cast(file_detail_cd as varchar)) =1 then '00000000' || cast(file_detail_cd)"
	        		+ " when char_length(cast(file_detail_cd as varchar)) =2 then '0000000' || cast(file_detail_cd)"
	        		+ " when char_length(cast(file_detail_cd as varchar)) =3 then '000000' || cast(file_detail_cd)"
	        		+ " when char_length(cast(file_detail_cd as varchar)) =4 then '00000' || cast(file_detail_cd)"
	        		+ " when char_length(cast(file_detail_cd as varchar)) =5 then '0000' || cast(file_detail_cd)"
	        		+ " when char_length(cast(file_detail_cd as varchar)) =6 then '000' || cast(file_detail_cd)"
	        		+ " when char_length(cast(file_detail_cd as varchar)) =7 then '00' || cast(file_detail_cd as varchar)"
	        		+ " when char_length(cast(file_detail_cd as varchar)) =8 then '0' || cast(file_detail_cd)"
	        		+ " end "
	        		+ " from (select cast(substring(max(file_detail_cd),10) as INTEGER)+1 as file_detail_cd from sopi_file_detail) sfm;"
	        		+ "");
	        if(rs.next())
	        {
	            String generatedId = prefix + rs.getInt(1);
	            return generatedId;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return null;
	}
}
