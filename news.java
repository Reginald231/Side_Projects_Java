package NewsBot;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class news{

    private static HttpURLConnection connection;
    private static String newsTweet;
    private static Random rand = new Random();

    public news(){}

    public static String getNews(){
        //Method1: Java.net.HttpURLConnection
        BufferedReader reader;
        String line;
        StringBuffer readerContent = new StringBuffer();
        try{
            URL url = new URL("https://newsapi.org/v2/top-headlines?country=us&apiKey=[Enter newsapi.org API key here]");
            connection = (HttpURLConnection)url.openConnection();

            //request setup
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);//time in milliseconds.
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            //A response code of '200' means a successful connection.


            if (status > 299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));

                while((line = reader.readLine()) != null){
                    readerContent.append(line);
                }

                reader.close();
            }
            else{
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                while((line = reader.readLine()) != null){
                    readerContent.append(line);
                }

                reader.close();
            }

           // System.out.println(readerContent.toString());

            newsTweet = parse(readerContent.toString());

        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            connection.disconnect();
        }

    return newsTweet;
    }
    public static String parse(String responseBody){
        JSONObject news = new JSONObject(responseBody);
        JSONArray newsArticles = news.getJSONArray("articles");
        int articleIndex = rand.nextInt(newsArticles.length());
        JSONObject article = newsArticles.getJSONObject(articleIndex);
        JSONObject source = article.getJSONObject("source");
        String newsSource = (source.getString("name")).replaceAll(" ","");

        if (newsSource.contains(".com")){
            newsSource = newsSource.replaceAll(".com", "");
        }else if (newsSource.contains(".net")){
            newsSource = newsSource.replaceAll(".net", "");
        }else if(newsSource.contains(".org")){
           newsSource = newsSource.replaceAll(".org", "" );
        }
        String title = article.getString("title");
        String url = article.getString("url");
        newsTweet = title + " #" + newsSource +" #News #Bot " + url;
        return newsTweet;
    }
}


