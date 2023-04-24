package ulaval.glo2003;

public class EnvironmentVariable {

    public static String getFloppaHostEmail(){
        return System.getenv("FLOPPA_HOST_EMAIL");
    }

    public static String getFloppaHostPassword(){
        return System.getenv("FLOPPA_HOST_PASSWORD");
    }

    public static String getFloppaMongoClusterUrl(){
        return System.getenv("FLOPPA_MONGO_CLUSTER_URL");
    }

    public static String getFloppaMongoDatabase(){
        return System.getenv("FLOPPA_MONGO_DATABASE");
    }
}
