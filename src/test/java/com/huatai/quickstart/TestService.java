package com.huatai.quickstart;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestService {
	
	public void test() {
        try {
            ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
            DataSource ds = ctx.getBean("dataSource", DataSource.class);
            Connection conn = ds.getConnection();
            Statement st = conn.createStatement();
            ResultSet rt = st.executeQuery("select * from t_user_info");
            while (rt.next()) {
                String test1 = rt.getString(2);
                System.out.println(test1);
            }
            rt.close();
            st.close();
            conn.close();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
        }
    }
	
	public static void main(String[] args) {
		TestService t = new TestService();
        t.test();
	}

}
