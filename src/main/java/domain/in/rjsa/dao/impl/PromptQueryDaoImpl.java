package domain.in.rjsa.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.ReturningWork;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import domain.in.rjsa.dao.PromptQueryDao;

@Repository("PromptQueryDao")
public class PromptQueryDaoImpl implements PromptQueryDao {

	@Autowired
	private SessionFactory sessionFactory;

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<HashMap<String, Object>> getData(String userText) {
		// native query
		String queryString = "SELECT * FROM AAACN4165C_2324.REGULAR24QDEDUCTEE ORDER BY ID DESC";
		List<HashMap<String, Object>> resultList = new ArrayList<HashMap<String, Object>>();

		try {
			Connection connection = getSession().doReturningWork(new ReturningWork<Connection>() {

				@Override
				public Connection execute(Connection connection) throws SQLException {
					// TODO Auto-generated method stub
					return connection;
				}
			});

			try (Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery(queryString)) {

				ResultSetMetaData metadata = resultSet.getMetaData();
				int columnCount = metadata.getColumnCount();

				while (resultSet.next()) {
					HashMap<String, Object> map = new LinkedHashMap<String, Object>();

					for (int i = 1; i <= columnCount; ++i) {
						String columnName = metadata.getColumnName(i);
						Object value = resultSet.getObject(i);

						map.put(columnName, value);
					}
					resultList.add(map);
				}

				// optional
				HashMap<String, Object> map = new LinkedHashMap<String, Object>();
				List<Object> dataTypesList = new ArrayList<Object>();
				for (int i = 1; i <= columnCount; ++i) {
					Object dataType = dataTypeConverter(metadata.getColumnClassName(i));
					dataTypesList.add(dataType);
				}
				map.put("dataTypes", dataTypesList);
				resultList.add(map);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return resultList;
	}

	public Object dataTypeConverter(String stringDataType) {
		HashMap<String, Class<?>> dataTypeMapping = new HashMap<>();
		dataTypeMapping.put("java.lang.String", String.class);
		dataTypeMapping.put("java.lang.Integer", Integer.class);
		dataTypeMapping.put("java.math.BigDecimal", Integer.class);
		dataTypeMapping.put("java.lang.Double", Double.class);
		dataTypeMapping.put("java.lang.Boolean", Boolean.class);
		dataTypeMapping.put("java.lang.Long", Long.class);
		dataTypeMapping.put("java.sql.Timestamp", Date.class);

		return dataTypeMapping.get(stringDataType);
	}

}
