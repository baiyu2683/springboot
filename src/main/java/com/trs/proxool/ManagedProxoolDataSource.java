package com.trs.proxool;

import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.logicalcobwebs.proxool.ProxoolFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

public class ManagedProxoolDataSource extends ProxoolDataSource implements
		DisposableBean {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ManagedProxoolDataSource.class);

	public void destroy() throws Exception {
		LOGGER.info("正在关闭Proxool连接池[{}]...",getAlias());
		ProxoolFacade.removeConnectionPool(getAlias());

	}

	@Override
	public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}
}
