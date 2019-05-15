package core.database;

import java.util.HashMap;
import java.util.Map;

import static core.database.AppsDataSource.AndroidSystemApp.*;
import static core.database.AppsDataSource.IOSSystemApp.*;

public class AppsDataSource {

    private Map<IOSSystemApp, String> iOSApps = new HashMap<>();
    private Map<AndroidSystemApp, AndroidApp> androidApps = new HashMap<>();

    public AppsDataSource() {
        initIOSApps();
        initAndroidApps();
    }

    public enum IOSSystemApp {
        App_Store, Apple_Store, Apple_Support, Apple_Watch, Calculator, Camera, Calendar, Classroom, Clips, Clock, Compass, FaceTime, Contacts, Feedback_Assistant, Find_Friends,
        Find_iPhone, Files, Game_Center, GarageBand, Apple_Heart, Health, Study, Home, iBooks, iCloud_Drive, iMovie, iTunes_Connect, iTunes_Remote, iTunes_Store, iTunes_U, Keynote,
        Logic_Remote, Mail, Maps, Measure, Messages, Music, Music_Memos, News, Notes, Numbers, Pages, Phone, Photo_Booth, Photos, Playgrounds, Podcasts, Reminders, Remote, Safari,
        Settings, Shortcuts, Siri, Stocks, Tips, Trailers, TV, Videos, Voice_Memos, Wallet, Weather, WWDC
    }

    public enum AndroidSystemApp {
        Bluetooth, Buddies_Widget, Calculator, Calendar, TouchWiz_Calendar_Widget, Camera, Analog_Clock_Widget, Contacts, Phone_Dialer, Downloads, Radio, Samsung_Game_Hub, Gmail,
        Messages, Internet_Browser, Play_Store, Memo, Music_Player, Samsung_Music_Player, Files_Explorer, Samsung_Account, Samsung_Apps, Search_Apps, Settings, Video_Player, Youtube
    }

    private void initIOSApps() {
        iOSApps.put(App_Store, "com.apple.AppStore");
        iOSApps.put(Apple_Store, "com.apple.store.Jolly");
        iOSApps.put(Apple_Support, "com.apple.supportapp");
        iOSApps.put(Apple_Watch, "com.apple.Bridge");
        iOSApps.put(IOSSystemApp.Camera, "com.apple.camera");
        iOSApps.put(IOSSystemApp.Calendar, "com.apple.mobilecal");
        iOSApps.put(Classroom, "com.apple.classroom");
        iOSApps.put(Clips, "com.apple.clips");
        iOSApps.put(Clock, "com.apple.mobiletimer");
        iOSApps.put(Compass, "com.apple.compass");
        iOSApps.put(FaceTime, "com.apple.facetime");
        iOSApps.put(IOSSystemApp.Contacts, "com.apple.MobileAddressBook");
        iOSApps.put(Feedback_Assistant, "com.apple.appleseed.FeedbackAssistant");
        iOSApps.put(Find_Friends, "com.apple.mobileme.fmf1");
        iOSApps.put(Find_iPhone, "com.apple.mobileme.fmip1");
        iOSApps.put(Files, "com.apple.DocumentsApp");
        iOSApps.put(Game_Center, "com.apple.gamecenter");
        iOSApps.put(GarageBand, "com.apple.mobilegarageband");
        iOSApps.put(IOSSystemApp.Calculator, "com.apple.calculator");
        iOSApps.put(Apple_Heart, "com.apple.Health");
        iOSApps.put(Health, "com.apple.Health");
        iOSApps.put(Study, "com.apple.Antimony");
        iOSApps.put(Home, "com.apple.Home");
        iOSApps.put(iBooks, "com.apple.iBooks");
        iOSApps.put(iCloud_Drive, "com.apple.iCloudDriveApp");
        iOSApps.put(iMovie, "com.apple.iMovie");
        iOSApps.put(iTunes_Connect, "com.apple.itunesconnect.mobile");
        iOSApps.put(iTunes_Remote, "com.apple.TVRemote");
        iOSApps.put(iTunes_Store, "com.apple.MobileStore");
        iOSApps.put(iTunes_U, "com.apple.itunesu");
        iOSApps.put(Keynote, "com.apple.Keynote");
        iOSApps.put(Logic_Remote, "com.apple.musicapps.remote");
        iOSApps.put(Mail, "com.apple.mobilemail");
        iOSApps.put(Maps, "com.apple.Maps");
        iOSApps.put(Measure, "com.apple.measure");
        iOSApps.put(IOSSystemApp.Messages, "com.apple.MobileSMS");
        iOSApps.put(Music, "com.apple.Music");
        iOSApps.put(Music_Memos, "com.apple.musicmemos");
        iOSApps.put(News, "com.apple.news");
        iOSApps.put(Notes, "com.apple.mobilenotes");
        iOSApps.put(Numbers, "com.apple.Numbers");
        iOSApps.put(Pages, "com.apple.Pages");
        iOSApps.put(Phone, "com.apple.mobilephone");
        iOSApps.put(Photo_Booth, "com.apple.Photo-Booth");
        iOSApps.put(Photos, "com.apple.mobileslideshow");
        iOSApps.put(Playgrounds, "com.apple.Playgrounds");
        iOSApps.put(Podcasts, "com.apple.podcasts");
        iOSApps.put(Reminders, "com.apple.reminders");
        iOSApps.put(Remote, "com.apple.Remote");
        iOSApps.put(Safari, "com.apple.mobilesafari");
        iOSApps.put(IOSSystemApp.Settings, "com.apple.Preferences");
        iOSApps.put(Shortcuts, "is.workflow.my.app");
        iOSApps.put(Siri, "com.apple.SiriViewService");
        iOSApps.put(Stocks, "com.apple.stocks");
        iOSApps.put(Tips, "com.apple.tips");
        iOSApps.put(Trailers, "com.apple.movietrailers");
        iOSApps.put(TV, "com.apple.tv");
        iOSApps.put(Videos, "com.apple.videos");
        iOSApps.put(Voice_Memos, "com.apple.VoiceMemos");
        iOSApps.put(Wallet, "com.apple.Passbook");
        iOSApps.put(Weather, "com.apple.weather");
        iOSApps.put(WWDC, "com.apple.wwdc");
    }

    public String getIOSApp(IOSSystemApp app) {
        return iOSApps.get(app);
    }

    private void initAndroidApps() {
        androidApps.put(Bluetooth, new AndroidApp("com.android.bluetooth", ""));
        androidApps.put(Buddies_Widget, new AndroidApp("com.sec.android.widgetapp.buddiesnow", ""));
        androidApps.put(AndroidSystemApp.Calculator, new AndroidApp("com.sec.android.app.calculator", ""));
        androidApps.put(AndroidSystemApp.Calendar, new AndroidApp("com.android.calendar", ""));
        androidApps.put(TouchWiz_Calendar_Widget, new AndroidApp("com.sec.android.widgetapp.TwCalendarAppWidget", ""));
        androidApps.put(AndroidSystemApp.Camera, new AndroidApp("com.sec.android.app.camera", ""));
        androidApps.put(Analog_Clock_Widget, new AndroidApp("com.sec.android.widgetapp.analogclock", ""));
        androidApps.put(AndroidSystemApp.Contacts, new AndroidApp("com.android.contacts", ""));
        androidApps.put(Phone_Dialer, new AndroidApp("com.android.phone", ""));
        androidApps.put(Downloads, new AndroidApp("com.android.providers.downloads.ui", ""));
        androidApps.put(Radio, new AndroidApp("com.sec.android.app.fm", ""));
        androidApps.put(Samsung_Game_Hub, new AndroidApp("com.ngmoco.gamehubmobage", ""));
        androidApps.put(Gmail, new AndroidApp("com.google.android.gm", ""));
        androidApps.put(AndroidSystemApp.Messages, new AndroidApp("com.sec.android.im", ""));
        androidApps.put(Internet_Browser, new AndroidApp("com.android.browser", ""));
        androidApps.put(Play_Store, new AndroidApp("com.android.vending", ""));
        androidApps.put(Memo, new AndroidApp("com.sec.android.app.memo", ""));
        androidApps.put(Music_Player, new AndroidApp("com.android.music", ""));
        androidApps.put(Samsung_Music_Player, new AndroidApp("com.samsung.music", ""));
        androidApps.put(Files_Explorer, new AndroidApp("com.sec.android.app.myfiles", ""));
        androidApps.put(Samsung_Account, new AndroidApp("com.osp.app.signin", ""));
        androidApps.put(Samsung_Apps, new AndroidApp("com.sec.android.app.samsungapps", ""));
        androidApps.put(Search_Apps, new AndroidApp("com.android.providers.applications", ""));
        androidApps.put(AndroidSystemApp.Settings, new AndroidApp("com.android.settings", ""));
        androidApps.put(Video_Player, new AndroidApp("com.sec.android.app.videoplayer", ""));
        androidApps.put(Youtube, new AndroidApp("com.google.android.youtube", ""));
    }

    public AndroidApp getAndroidApp(AndroidSystemApp app) {
        return androidApps.get(app);
    }

    public class AndroidApp {
        public final String packageId;
        public final String activityId;

        AndroidApp(String packageId, String activityId) {
            this.packageId = packageId;
            this.activityId = activityId;
        }
    }
// TODO: 2019-05-15 Complete Android activities list
}
