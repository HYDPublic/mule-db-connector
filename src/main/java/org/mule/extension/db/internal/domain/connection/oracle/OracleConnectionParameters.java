/*
 * Copyright (c) MuleSoft, Inc.  All rights reserved.  http://www.mulesoft.com
 * The software in this package is published under the terms of the CPAL v1.0
 * license, a copy of which has been included with this distribution in the
 * LICENSE.txt file.
 */
package org.mule.extension.db.internal.domain.connection.oracle;

import org.mule.extension.db.internal.domain.connection.BaseDbConnectionParameters;
import org.mule.extension.db.internal.domain.connection.DataSourceConfig;
import org.mule.runtime.extension.api.annotation.param.Optional;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.Password;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

/**
 * /**
 * {@link DataSourceConfig} implementation for Oracle databases.
 *
 * @since 1.0
 */
public class OracleConnectionParameters extends BaseDbConnectionParameters implements DataSourceConfig {

  static final String DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
  private static final String JDBC_URL_PREFIX = "jdbc:oracle:thin:@";

  /**
   * Configures the host of the database
   */
  @Parameter
  @Placement(order = 1)
  private String host;

  /**
   * Configures the port of the database
   */
  @Parameter
  @Optional(defaultValue = "1521")
  @Placement(order = 2)
  private Integer port;

  /**
   * The user that is used for authentication against the database
   */
  @Parameter
  @Optional
  @Placement(order = 3)
  private String user;

  /**
   * The password that is used for authentication against the database
   */
  @Parameter
  @Optional
  @Placement(order = 4)
  @Password
  private String password;

  /**
   * The name of the database instance
   */
  @Parameter
  @Optional
  @Placement(order = 5)
  private String instance;


  @Override
  public String getUrl() {
    return generateUrl();
  }

  @Override
  public String getDriverClassName() {
    return DRIVER_CLASS_NAME;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUser() {
    return user;
  }

  private String generateUrl() {
    StringBuilder buf = new StringBuilder(JDBC_URL_PREFIX);
    buf.append(host);
    buf.append(":");
    buf.append(port);
    if (instance != null) {
      buf.append(":");
      buf.append(instance);
    }

    return buf.toString();
  }
}
