package org.eclipse.rap.rwt.performance.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.eclipse.rap.rwt.performance.IPerformanceStorage;

public class H2PerformanceStorage implements IPerformanceStorage {

	private static final String JDBC_URI = "jdbc:h2:tcp://localhost/~/performance";
	private static final String USERNAME = "sa";
	private static final String PASSWORD = "";
	private Connection con;

	public void putResults(TestCase test, List frames) {
		int testId = putTest(test);
		int runId = putTestRun(testId);
		putFrames(runId, frames);
	}

	private void putFrames(int runId, List frames) {
		for (Iterator iterator = frames.iterator(); iterator.hasNext();) {
			Long frameTime = (Long) iterator.next();
			putFrame(runId, frameTime.longValue());		
		}
	}

	private void putFrame(int runId, long frameTime) {
		try {
			Connection con = getConnection();
			String sql = "INSERT INTO iterations( TESTRUNID, RESULT) VALUES (?, ?);";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, runId);
			stmt.setLong(2, frameTime);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private int putTestRun(int testId) {
		Connection con = getConnection();
		int runId = 0;
		try {
			String sql = "INSERT INTO testruns( TESTID , DATE) VALUES (?, NOW());";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, testId);
			stmt.execute();
			runId = getLastInsertId(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return runId;
	}

	private int putTest(TestCase test) {
		Connection con = getConnection();
		String className = test.getClass().getName();
		String testName = test.getName();
		int testId = 0;
		try {
			String sql = "INSERT INTO tests( TESTNAME , TESTCLASS) VALUES (?, ?);";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, testName);
			stmt.setString(2, className);
			stmt.execute();
			testId = getLastInsertId(stmt);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return testId;
	}

	private int getLastInsertId(PreparedStatement stmt) throws SQLException {
		ResultSet keys = stmt.getGeneratedKeys();
		keys.next();
		return keys.getInt(1);
	}

	private Connection getConnection() {
		Connection result = null;
		if( con != null ) {
			result = con;
		} else {
			try {
				con = DriverManager.getConnection(JDBC_URI, USERNAME, PASSWORD);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public List getAggregatedResults(String testName) {
		return null;
	}

	public H2PerformanceStorage() {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}