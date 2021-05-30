package haris.org.futureschool.model;

public class KontakModel {
    private String telepon_sekolah, website_sekolah, email_sekolah, facebook_sekolah, whatsapp_sekolah, telegram_sekolah, instagram_sekolah, twitter_sekolah, youtube_sekolah;

    public KontakModel(String telepon_sekolah, String website_sekolah, String email_sekolah, String facebook_sekolah, String whatsapp_sekolah, String telegram_sekolah, String instagram_sekolah, String twitter_sekolah, String youtube_sekolah) {
        this.telepon_sekolah = telepon_sekolah;
        this.website_sekolah = website_sekolah;
        this.email_sekolah = email_sekolah;
        this.facebook_sekolah = facebook_sekolah;
        this.whatsapp_sekolah = whatsapp_sekolah;
        this.telegram_sekolah = telegram_sekolah;
        this.instagram_sekolah = instagram_sekolah;
        this.twitter_sekolah = twitter_sekolah;
        this.youtube_sekolah = youtube_sekolah;
    }

    public String getTelepon_sekolah() {
        return telepon_sekolah;
    }

    public String getWebsite_sekolah() {
        return website_sekolah;
    }

    public String getEmail_sekolah() {
        return email_sekolah;
    }

    public String getFacebook_sekolah() {
        return facebook_sekolah;
    }

    public String getWhatsapp_sekolah() {
        return whatsapp_sekolah;
    }

    public String getTelegram_sekolah() {
        return telegram_sekolah;
    }

    public String getInstagram_sekolah() {
        return instagram_sekolah;
    }

    public String getTwitter_sekolah() {
        return twitter_sekolah;
    }

    public String getYoutube_sekolah() {
        return youtube_sekolah;
    }
}
