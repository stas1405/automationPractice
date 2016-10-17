package main.resources;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@Component
public class JdbcDAO {
	
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;

	/** Converting response from Database to String of values from rows
	 * @param List of Maps
	 * @return List<String>
	 */
	public List<String> convertToList(List<Map<String, Object>> input) {
		 List<String> pages = new LinkedList<>();
		 for (Map<String, Object> m : input){			 
				Collection<Object> list = new ArrayList<Object>(m.values());
				list.forEach(value -> pages.add((String) value));
		 }
       return pages;
	}
	
	/** Converting response from Database to map of values from rows
	 * @param List of Maps
	 * @return Map<String, String>
	 */
	public Map<String, String> convertMapValueToValueFromResponse(List<Map<String, Object>> input) {
        Map<String, String> returnMap = new HashMap<>();
        for (Map<String, Object> map : input) {
        	Collection<Object> list = new ArrayList<Object>(map.values());
        	String[] keyvalue = list.toArray(new String().split(","));
        	returnMap.put(keyvalue[0], keyvalue[1]);
		 	}
        return returnMap;
	}
	
	/**
	 * Generic method to get list of maps(column to Value) from Database
	 * @param sql
	 * @param pageCollection
	 * @return List of Maps
	 */
	public List<Map<String, Object>> getCollection(String sql, String pageCollection) {
		return jdbcTemplate.queryForList(sql, pageCollection);	
	}
	public List<Map<String, Object>> getCollection(String sql) {
		return jdbcTemplate.queryForList(sql);	
	}
	
	public List<?> getAsList(String sql) {
		return jdbcTemplate.queryForList(sql);	
	}
	
	/**
	 * Configuration
	 */
	@Autowired
	public void setDataSource(DataSource dataSource) {
		try {
			this.jdbcTemplate = new JdbcTemplate(dataSource);
		} catch (CannotGetJdbcConnectionException e) {
			assertThat("Unable to connect to Database ", is(e));
		} 
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public DataSource getDataSource() {
		return dataSource;
	}
}
