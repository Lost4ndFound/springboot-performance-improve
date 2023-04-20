//package com.study.config;
//
//import org.apache.catalina.connector.Connector;
//import org.apache.coyote.http11.Http11NioProtocol;
//import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
//import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
//import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * 增加内嵌Tomcat的最大连接数
// * @author: lsw
// * @date: 2023/4/19 15:48
// */
////@Configuration
//public class TomcatConfig {
//
//    @Bean
//    public ConfigurableServletWebServerFactory webServerFactory() {
//        TomcatServletWebServerFactory tomcatFactory = new TomcatServletWebServerFactory();
//        tomcatFactory.addConnectorCustomizers(new MyTomcatConnectorCustomizer());
//        // 设置端口号
//        tomcatFactory.setPort(8005);
//        // 设置访问根路径
//        tomcatFactory.setContextPath("/api");
//        return tomcatFactory;
//    }
//
//    class MyTomcatConnectorCustomizer implements TomcatConnectorCustomizer {
//        public void customize(Connector connector) {
//            Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
//            // 设置最大连接数
//            protocol.setMaxConnections(20000);
//            // 设置最大线程数
//            protocol.setMaxThreads(2000);
//            // 设置连接超时时间
//            protocol.setConnectionTimeout(30000);
//        }
//    }
//
//}
