package hex;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@SpringBootApplication
@ComponentScan(basePackages = {"hex"})
public class PasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PasApplication.class, args);
	}

	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection securityCollection = new SecurityCollection();
				securityCollection.addPattern("/*");
				securityConstraint.addCollection(securityCollection);
				context.addConstraint(securityConstraint);
			}
		};

		tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());

		return tomcat;
	}

	@Bean(name = "restTemplate")
	public RestTemplate restTemplate(RestTemplateBuilder builder) throws NoSuchAlgorithmException, KeyManagementException {

		TrustManager[] trustAllCerts = new TrustManager[] {
				new X509TrustManager() {
					public X509Certificate[] getAcceptedIssuers() {
						return new X509Certificate[0];
					}
					public void checkClientTrusted(
							X509Certificate[] certs, String authType) {
					}
					public void checkServerTrusted(
							X509Certificate[] certs, String authType) {
					}
				}
		};
		SSLContext sslContext = SSLContext.getInstance("SSL");
		sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
		CloseableHttpClient httpClient = HttpClients.custom()
				.setSSLContext(sslContext)
				.setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
				.build();
		HttpComponentsClientHttpRequestFactory customRequestFactory = new HttpComponentsClientHttpRequestFactory();
		customRequestFactory.setHttpClient(httpClient);
		return builder.requestFactory(() -> customRequestFactory).build();
	}

	private Connector httpToHttpsRedirectConnector() {
		Connector connector = new Connector((TomcatServletWebServerFactory.DEFAULT_PROTOCOL));
		connector.setScheme("http");
		connector.setPort(3000);
		connector.setSecure(false);
		connector.setRedirectPort(3443);
		return connector;
	}

}
