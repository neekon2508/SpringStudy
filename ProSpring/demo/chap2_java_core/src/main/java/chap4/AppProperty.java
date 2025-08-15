package chap4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class AppProperty {
    private String applicationHome;
    private String userHome;

    public String getApplicationHome() {
        return applicationHome;
    }

    @Autowired
    public void setApplicationHome( @Value("${application.home}") String applicationHome) {
        this.applicationHome = applicationHome;
    }

    public String getUserHome() {
        return userHome;
    }

    @Autowired
    public void setUserHome(@Value("${user.home}") String userHome) {
        this.userHome = userHome;
    }

    @Override
    public String toString() {
        return "applicationHome:"+applicationHome
        +"; userHome:"+userHome;
    }
}
