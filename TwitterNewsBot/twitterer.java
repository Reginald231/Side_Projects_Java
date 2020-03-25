package NewsBot;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class twitterer {
    public static void main(String[] args){
        final int TWEET_LENGTH = 280; //Official lenght of Tweets for Twitter.
        NewsBot.news n = new NewsBot.news();
        System.out.println("Retreiving news...");
        String tweet = n.getNews();

        if(tweet.length() <= TWEET_LENGTH) {
            System.out.println("Tweet length: " + tweet.length());
            System.out.println("Tweet: " + tweet);
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey("[CONSUMER KEY]")
                .setOAuthConsumerSecret("[CONSUMER KEY SECRET]")
                .setOAuthAccessToken("[ACCESS TOKEN]")
                .setOAuthAccessTokenSecret("[ACCESS TOKEN SECRET]");
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();

        try {
            twitter.updateStatus(tweet);
           // System.out.println("Tweeted new status.");
        }
        catch(Exception TWE){
            TWE.printStackTrace();
        }
        }
        else{//For now. Will be removed in the future. Exit the program if a tweet less than TWEET_LENGTH characters has been retreived.
                System.out.println("The tweet was too long to post.");
                System.exit(1);
        }
    }
}
