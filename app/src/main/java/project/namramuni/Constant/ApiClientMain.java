package project.namramuni.Constant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import project.namramuni.ViewModels.List.RecentVideoList;
import project.namramuni.ViewModels.List.Shibirformlist;
import project.namramuni.ViewModels.Main.ActivityBoxContent;
import project.namramuni.ViewModels.Main.GurudevPlaylistResponse;
import project.namramuni.ViewModels.Main.NewVideoResponse;
import project.namramuni.ViewModels.Main.ShibirFormlistResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import project.namramuni.ViewModels.Main.ActivityBox;
import project.namramuni.ViewModels.Main.ContactListResponse;
import project.namramuni.ViewModels.Main.DonatelistResponse;
import project.namramuni.ViewModels.Main.EventResponse;
import project.namramuni.ViewModels.Main.GallerylistResponse;
import project.namramuni.ViewModels.Main.GurudevReadlistResponse;
import project.namramuni.ViewModels.Main.GurudevVideolistResponse;
import project.namramuni.ViewModels.Main.HomeListResponse;
import project.namramuni.ViewModels.Main.LearnResponse;
import project.namramuni.ViewModels.Main.PlayListResponse;
import project.namramuni.ViewModels.Main.PunyaBankListResponse;
import project.namramuni.ViewModels.Main.UserResponse;

public class ApiClientMain {
    private static ApiInterface ApiInterface;
    // interface for Retrofit api
    //public static final String prefixUrl = "index.php/api/";
    private static StringBuilder builder = new StringBuilder();
    public static final String URL = builder.append(ApplicationData.URL).toString();
    public static final String MEDIA_TYPE_STRING = "text/plain";

    public static ApiInterface getApiClient() {
        return getApiClient(URL);
    }

    public static ApiInterface getApiClient(String url) {
        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient client = new OkHttpClient();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        /*okHttpClient.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                return onOnIntercept(chain);
            }
        });*/
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface = retrofit.create(ApiInterface.class);
        return ApiInterface;
    }

    public interface OnConnectionTimeoutListener {
        void onConnectionTimeout();
    }

    public interface ApiInterface {
        @GET("donate_list")
        Call<DonatelistResponse> getDonateList();

        @GET("gallery_list")
        Call<GallerylistResponse> getGalleryList();

        @GET("gallery_sublist")
        Call<GallerylistResponse> getSubGalleryList(@Query("gallery_id") String id);

        @GET("home")
        Call<HomeListResponse> getHome(@Query("key") String key,
                                       @Query("tkey") String tkey,
                                       @Query("user_id") String id);

         @GET("activity_score")
        Call<HomeListResponse> getScore(@Query("key") String key,
                                        @Query("tkey")String tkey,
                                        @Query("user_id") String id);



        @GET("audio")
        Call<GurudevVideolistResponse> getAudio();

        @GET("video")
        Call<GurudevVideolistResponse> getGurudevVideo();

        @GET("youtube_playlist")
        Call<NewVideoResponse> getGurudevPlaylist(@Query("key") String key,
                                                         @Query("tkey") String tkey);

        @GET("gurudev_read")
        Call<GurudevReadlistResponse> getReadList(@Query("file_type") String type);

        @GET("gurudev_schedule")
        Call<EventResponse> getEvent(@Query("tkey") String tkey,
                                     @Query("key") String key
                                        );

        @GET("recent_uploads")
        Call<NewVideoResponse> getRecentVideo(@Query("tkey") String tkey,
                                               @Query("key") String key);

        @GET("divinities")
        Call<PunyaBankListResponse> getDivinity(@Query("divinity_type") String type);

        @GET("learn_list")
        Call<LearnResponse> getLearn(@Query("file_type") String file_type);

        @GET("learn_list")
        Call<LearnResponse> getLearn();

        @GET("gurudev_shibir")
        Call<EventResponse> getShibir(@Query("tkey") String tkey,
                                      @Query("key") String key ,
                                      @Query("user_id") String id);
        @GET("luckydraw_form")
        Call<ShibirFormlistResponse> getLuckydraw(@Query("tkey") String tkey,
                                      @Query("key") String key ,
                                      @Query("user_id") String id);

        @GET("center_list")
        Call<ContactListResponse> getContact();

        @GET("playlist_song")
        Call<PlayListResponse> getPlayList(@Query("playlist_id") String id,
                                           @Query("playlist_type") String type);

        @GET("inside_app")
        Call<UserResponse> getIMEI(@Query("imei") String id);

        @GET("verify_otp")
        Call<UserResponse> getOTP(@Query("otp") String otp,
                                  @Query("country_code") String code,
                                  @Query("mobile_no") String mobile,
                                  @Query("imei") String imei,
                                  @Query("is_status") String is_status);

        @GET("song_list")
        Call<PlayListResponse> getPlayList(@Query("playlist_type") String type);

         @GET("search")
        Call<PlayListResponse> getPlayListSearch(@Query("type") String type);

        @GET("activity_dtl")
        Call<ActivityBoxContent> getActivityList(@Query("user_id") String id,
                                                 @Query("activity_id") String aid);

        @GET("complete_activity")
        Call<ActivityBoxContent> getActivityComplete(@Query("user_id") String id,
                                                     @Query("sub_id") String aid,
                                                     @Query("is_status") String status);

        @GET("activity_list")
        Call<ActivityBox> getActivityList(@Query("user_id") String id);

        @GET("test")
        Call<PlayListResponse> test();

        @FormUrlEncoded
        @POST("profile")
        Call<UserResponse> getProfile(@Field("user_id") String userid);




        @FormUrlEncoded
        @POST("state_list")
        Call<EventResponse> getStatelist( @Field("key") String key,
                                          @Field("tkey") String tkey,
                                          @Field("country_id") String  country_id);
        @FormUrlEncoded
        @POST("city_list")
        Call<EventResponse> getCitylist( @Field("key") String key,
                                          @Field("tkey") String tkey,
                                          @Field("state_id") String  state_id);



        @GET("get_otp")
        Call<UserResponse> getLogin(@Query("country_code") String code,
                                    @Query("mobile_no") String mobile);


        @FormUrlEncoded
        @POST("shibir_form")
        Call<ShibirFormlistResponse> getShibirForm(@Field("key") String key,
                                                   @Field("tkey") String tkey,
                                                   @Field("shibir_id") String shibir_id,
                                                   @Field("user_id") String user_id
                                                   );



        @FormUrlEncoded
        @POST("update_user_status")
        Call<ShibirFormlistResponse> UpdateLucyDraw(@Field("key") String key,
                                                   @Field("tkey") String tkey,
                                                   @Field("user_status") String user_status,
                                                   @Field("event_id") String event_id  ,

                                                    @Field("luckydraw_id") String luckydraw_id,
                                                    @Field("user_id") String user_id
                                                   );
        @FormUrlEncoded
        @POST("youtube_playlist_items")
        Call<NewVideoResponse> getvideollist(@Field("key") String key,
                                            @Field("tkey") String tkey,
                                            @Field("playlist_id") String playlist_id

                                                   );

        @FormUrlEncoded
        @POST("search")
        Call<NewVideoResponse> getSearch(@Field("key") String key,
                                             @Field("tkey") String tkey,
                                             @Field("keyword") String keyword

        );

        @FormUrlEncoded
        @POST("shibir_join")
        Call<ShibirFormlistResponse> getShibirjoin(@Field("key") String key,
                                                   @Field("tkey") String tkey,
                                                   @Field("shibir_id") String shibir_id,
//                                                   @Field("profile_image") String profile_image,
                                                   @Field("name") String name,

                                                   @Field("address") String address,
                                                   @Field("city") String city,
                                                   @Field("state") String state,
                                                   @Field("pincode") String pincode,
                                                   @Field("contact_no") String contact_no,
                                                   @Field("dob") String dob,
                                                   @Field("age") String age,
                                                   @Field("marrital_status") String marrital_status,
                                                   @Field("occupation") String occupation,
                                                   @Field("ans_one") String ans_one,
                                                   @Field("ans_two") String ans_two,
                                                   @Field("ans_three") String ans_three,
                                                   @Field("ans_four") String ans_four,
                                                   @Field("mc_ans_one") String mc_ans_one,
                                                   @Field("mc_ans_two") String mc_ans_two,
                                                   @Field("mc_ans_three") String mc_ans_three,
                                                   @Field("mc_ans_four") String mc_ans_four,
                                                   @Field("user_id") String user_id
        );

       /* @GET("login")
        Call<Main2Response> getLogin(@Query("email") String username,
                                     @Query("password") String password,
                                     @Query("player_id") String playerid,
                                     @Query("device_id") String imei,
                                     @Query("key") String key,
                                     @Query("tkey") String tkey);

        @FormUrlEncoded
        @POST("secure_app")
        Call<MainResponse> secure_app(@Field("player_id") String player_id,
                                      @Field("device_id") String imei,
                                      @Field("key") String key,
                                      @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("calendar")
        Call<Object> shaowcalendar1(@Field("user_id") String id,
                                    @Field("device_id") String imei,
                                    @Field("key") String key,
                                    @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("change_password")
        Call<Main2Response> changePassword(@Field("user_id") String id,
                                           @Field("current_password") String oldpass,
                                           @Field("new_password") String newpass,
                                           @Field("device_id") String imei,
                                           @Field("key") String key,
                                           @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("forgot_password")
        Call<MainResponse> ForgotPassword(@Field("email") String email,
                                          @Field("device_id") String imei,
                                          @Field("key") String key,
                                          @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("profile")
        Call<UserResponse> getProfile(@Field("user_id") String userid,
                                      @Field("device_id") String imei,
                                      @Field("key") String key,
                                      @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("type")
        Call<TypeResponse> getType(@Field("device_id") String imei,
                                   @Field("key") String key,
                                   @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("invities")
        Call<TypeResponse> listUsers(@Field("user_id") String userid,
                                     @Field("device_id") String imei,
                                     @Field("key") String key,
                                     @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("invities")
        Call<TypeResponse> listUsers(@Field("user_id") String userid,
                                     @Field("reminder_id") String id,
                                     @Field("device_id") String imei,
                                     @Field("key") String key,
                                     @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("dashboard")
        Call<Object> shaowData(@Field("user_id") String id,
                               @Field("device_id") String imei,
                               @Field("key") String key,
                               @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("dashboard")
        Call<Object> shaowData(@Field("user_id") String id);

        @FormUrlEncoded
        @POST("chat_list")
        Call<InboxResponse> shaowInbox(@Field("user_id") String id,
                                       @Field("device_id") String imei,
                                       @Field("key") String key,
                                       @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("dashboard")
        Call<Object> shaowData(@Field("user_id") String id,
                               @Field("date_by") String date_by,
                               @Field("device_id") String imei,
                               @Field("key") String key,
                               @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("message_list")
        Call<ChatResponse> shaowChatList(@Field("user_id") String id,
                                         @Field("reminder_id") String reminder_id,
                                         @Field("device_id") String imei,
                                         @Field("key") String key,
                                         @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("complete_reminder")
        Call<MainResponse> CompleteReminder(@Field("user_id") String id,
                                            @Field("reminder_id") String reminder_id,
                                            @Field("device_id") String imei,
                                            @Field("key") String key, @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("sent_date")
        Call<MainResponse> CompleteCustomerCode(@Field("sent_date") String date,
                                                @Field("r_id") String reminder_id,
                                                @Field("device_id") String imei,
                                                @Field("key") String key,
                                                @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("complete_reminder")
        Call<MainResponse> CompleteReminder1(@Field("user_id") String id,
                                             @Field("gms") String gms,
                                             @Field("reminder_id") String reminder_id,
                                             @Field("device_id") String imei,
                                             @Field("key") String key,
                                             @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("device_change")
        Call<MainResponse> ChangeDevice(@Field("user_id") String id,
                                        @Field("device_id") String imei,
                                        @Field("key") String key,
                                        @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("sentmsg")
        Call<MainResponse> addChat(@Field("user_id") String id,
                                   @Field("reminder_id") String reminder_id,
                                   @Field("msg_text") String msg,
                                   @Field("device_id") String imei,
                                   @Field("key") String key,
                                   @Field("tkey") String tkey);

        //        @FormUrlEncoded
        @GET("get_leavelist")
        Call<Object> shaowLeave(@Query("user_id") String id,
                                @Query("device_id") String imei,
                                @Query("key") String key,
                                @Query("tkey") String tkey);

        @FormUrlEncoded
        @POST("order_karigarlist")
//        @POST("karigar_list")
        Call<KarigarResponse> shaowKarigar(@Field("user_id") String id,
                                           @Field("device_id") String imei,
                                           @Field("key") String key,
                                           @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("all_code")
        Call<KarigarResponse> shaowCustomer(@Field("user_id") String id,
                                            @Field("device_id") String imei,
                                            @Field("key") String key,
                                            @Field("tkey") String tkey);


        @FormUrlEncoded
        @POST("assign_by_me")
        Call<DashBoardResponse> shaowassignbyme(@Field("user_id") String id,
                                                @Field("device_id") String imei,
                                                @Field("key") String key,
                                                @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("assign_to_me")
        Call<DashBoardResponse> shaowassigntome(@Field("user_id") String id,
                                                @Field("device_id") String imei,
                                                @Field("key") String key,
                                                @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("order_details")
//        @POST("task_detail")
        Call<DashBoardResponse> shaowKarigarTask(@Field("user_id") String id,
                                                 @Field("karigar_id") String kid,
                                                 @Field("device_id") String imei,
                                                 @Field("key") String key,
                                                 @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("code_data")
        Call<DashBoardResponse> shaowCodeData(@Field("user_id") String id,
                                              @Field("code_name") String code_name,
                                              @Field("device_id") String imei,
                                              @Field("key") String key,
                                              @Field("tkey") String tkey);

        @FormUrlEncoded
        @POST("reminderdata")
        Call<Object> shaowTaskDetail(@Field("reminder_id") String id,
                                     @Field("device_id") String imei,
                                     @Field("key") String key,
                                     @Field("tkey") String tkey);

*/
//        @Headers("Token: " +ApplicationData.header)
//        @GET("Project/admin_assigned_prjs")
//        Call<MinutsMeetingResponse> listNewProj(
//                @Query("user_id") String user_id);


    }


}
