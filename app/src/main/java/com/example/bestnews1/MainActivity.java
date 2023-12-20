package com.example.bestnews1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    HashMap<String,String>hashMap = new HashMap<>();
    ArrayList< HashMap<String,String> > arrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.lisView);


        createTable();


        MyAdapter myAdapter = new MyAdapter();
        listView.setAdapter(myAdapter);
    }

    //=================================================
    //-------------------------------------------------

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {

            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View myView = inflater.inflate(R.layout.item, viewGroup, false);

            ImageView itemCover = myView.findViewById(R.id.itemCover);
            TextView itemCat = myView.findViewById(R.id.itemCover);
            TextView itemTitle = myView.findViewById(R.id.itemTitle);
            TextView itemDes = myView.findViewById(R.id.itemDes);
            LinearLayout layItem = myView.findViewById(R.id.layItem);



            HashMap<String, String> hashMap = arrayList.get(position);
            String cat = hashMap.get("cat");
            String image_url = hashMap.get("Image_url");
            String title = hashMap.get("title");
            String des = hashMap.get("des");

//Alt Enter
            Picasso.get().load(image_url)
                    .placeholder(R.drawable.jpg)               // placeHOlder jotokhon oi pic ta lode na hoi totokhon sha pic ta thak ba
                    .into(itemCover);

            itemCat.setText(cat);
            itemTitle.setText(title);
            itemDes.setText(des);


            Random rnd = new Random();
            int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
            itemCat.setBackgroundColor(color);

            layItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    NewsDetails.TITLE =title;
                    NewsDetails.DESCRIPTION =des;

                    Bitmap bitmap = ((BitmapDrawable) itemCover.getDrawable()).getBitmap();
                    NewsDetails.MY_BITMAP = bitmap;



                    startActivity( new Intent(MainActivity.this,NewsDetails.class));


                }
            });


            return myView;
        }
    }
    //===================== akhana code inmplement korta hoba jata kora kono somo na hoi
    ///////////////////////////////////
    /////**********************************

    private void createTable(){

        hashMap = new HashMap<>();
        hashMap.put("cat","TECH");
        hashMap.put("Image_url","data:image/jpeg;base64,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");
        hashMap.put("title","জানুয়ারিতে মার্কিন সেনেটে সাক্ষ্য দেবেন টিকটক ও মেটার সিইও");
        hashMap.put("des","শীঘ্রই মার্কিন সেনেটের শুনানিতে সাক্ষ্য দিতে যাচ্ছেন সামাজিক মাধ্যম কোম্পানি মেটা, টিকটক, স্ন্যাপ ও ডিসকর্ডের প্রধান নির্বাহীরা।\n" +
                "\n" +
                "বুধবার সেনেটের জুডিশিয়ারি কমিটি বলেছে, অনলাইনে শিশু নিপিড়ণের ঘটনা নিয়ে প্রস্তাবিত এ শুনানি হবে ২০২৪ সালের ৩১ জানুয়ারি।\n" +
                "\n" +
                "সেনেট প্যানেলের ডেমোক্র্যাটিক অংশের চেয়ারম্যান সেনেটর ডিক ডার্বিন ও রিপাবলিকান অংশের প্রধান লিন্ডজি গ্রেহাম বলেন, প্রাথমিকভাবে এ শুনানীতে অংশ নিতে চায়নি ডিসকর্ড ও এক্স।\n" +
                "\n" +
                "“এখন যেহেতু পাঁচ কোম্পানিই সহযোগিতার ইচ্ছা পোষণ করেছে, তাই আমরা কোম্পানিগুলোর সিইও’দের বক্তব্য শুনতে অধীর আগ্রহে অপেক্ষা করছি।” --বিবৃতিতে বলেন সেনেটের দুই শীর্ষ নেতা।\n" +
                "\n" +
                "মার্চের পর মার্কিন আইন প্রণেতাদের সামনে টিকটক সিইও শউ জি চিউ’র পুনরায় সাক্ষ্য দেওয়ার ঘটনা হতে যাচ্ছে এটি। সে সময় চীনা মালিকানাধীন কোম্পানিটির প্রধানকে বিভিন্ন প্রশ্নবাণে বিদ্ধ করেছিলেন সেনেট সদস্যরা। এর মধ্যে অ্যাপটি শিশুদের মানসিক স্বাস্থ্যের ক্ষতি করছে, এমন অভিযোগও ছিল।\n" +
                "\n" +
                "মার্কিন কংগ্রেসের প্রস্তাবিত আইন অনুসারে, এতে বাইডেন প্রশাসনকে নতুন ক্ষমতা দেওয়া হবে, যার মাধ্যমে টিকটকের মতো বিদেশী সামাজিক মাধ্যমে মার্কিন ব্যবহারকারীদের প্রবেশাধিকার বন্ধ হয়ে যাওয়ার ঝুঁকি রয়েছে। যুক্তরাষ্ট্রে অ্যাপটির ব্যবহারকারী সংখ্যা ১৫ কোটির বেশি।\n" +
                "\n" +
                "ডার্বিন ও গ্রেহাম বলেন, এ শুনানির মাধ্যমে অনলাইনে শিশুদের নিরাপত্তা দেওয়ার ক্ষেত্রে ব্যর্থ হওয়ার বিষয়টি নিয়ে শীর্ষ কোম্পানিগুলোর সিইও’দেরকে চাপ দেওয়ার সুযোগ পাবেন কমিটির সদস্যরা।\n" +
                "\n" +
                "এ শুনানিতে সাক্ষ্য দেবেন ফেইসবুক ও ইনস্টাগ্রামের মালিক কোম্পানি মেটা’র সিইও মার্ক জাকারবার্গ, এক্স-এর সিইও লিন্ডা ইয়াকারিনো, স্ন্যাপের সিইও ইভান স্পিগেল ও ডিসকর্ডের সিইও জেসন সাইট্রন।\n" +
                "\n" +
                "“শীর্ষ প্রযুক্তি কোম্পানিগুলো যে অনলাইনে নিজেদের ও শিশুদের নিরাপত্তা প্রদানে ব্যর্থ হয়েছে, সে প্রশ্নের জবাব দিতে হবে।” --বলেন ডার্বিন ও গ্রেহাম।\n" +
                "\n" +
                "এ বছর বেশ কয়েকটি বিলে অনুমোদন দিয়েছে সেনেট কমিটি। এর একটি হল, অনলাইনে শিশুদের যৌন নিপিড়ণ বিষয়ক কনটেন্টে প্রযুক্তি কোম্পানিগুলো যে নাগরিক ও অপরাধ আইনের আওতামুক্ত ছিল, সে সুবিধা সরিয়ে ফেলা। বিলটি উত্থাপিত হয়েছিল ২০২০ সালে।\n" +
                "\n" +
                "এর পাশাপাশি, অনলাইনে শিশুদের ওপর যৌন নিপিড়ণ ঠেকাতে জাতীয় কমিশন গঠন এবং এমন অপরাধের তদন্ত ও বিচার ব্যবস্থা আধুনিকায়নের মতো বিষয়গুলোও উল্লেখ করা হয়েছে আরও দুটি বিলে।");
        arrayList.add(hashMap);



        ///////////////////////////////////////////

        hashMap = new HashMap<>();
        hashMap.put("cat","TECH");
        hashMap.put("Image_url","data:image/jpeg;base64,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");
        hashMap.put("title","জানুয়ারিতে মার্কিন সেনেটে সাক্ষ্য দেবেন টিকটক ও মেটার সিইও");
        hashMap.put("des","শীঘ্রই মার্কিন সেনেটের শুনানিতে সাক্ষ্য দিতে যাচ্ছেন সামাজিক মাধ্যম কোম্পানি মেটা, টিকটক, স্ন্যাপ ও ডিসকর্ডের প্রধান নির্বাহীরা।\n" +
                "\n" +
                "বুধবার সেনেটের জুডিশিয়ারি কমিটি বলেছে, অনলাইনে শিশু নিপিড়ণের ঘটনা নিয়ে প্রস্তাবিত এ শুনানি হবে ২০২৪ সালের ৩১ জানুয়ারি।\n" +
                "\n" +
                "সেনেট প্যানেলের ডেমোক্র্যাটিক অংশের চেয়ারম্যান সেনেটর ডিক ডার্বিন ও রিপাবলিকান অংশের প্রধান লিন্ডজি গ্রেহাম বলেন, প্রাথমিকভাবে এ শুনানীতে অংশ নিতে চায়নি ডিসকর্ড ও এক্স।\n" +
                "\n" +
                "“এখন যেহেতু পাঁচ কোম্পানিই সহযোগিতার ইচ্ছা পোষণ করেছে, তাই আমরা কোম্পানিগুলোর সিইও’দের বক্তব্য শুনতে অধীর আগ্রহে অপেক্ষা করছি।” --বিবৃতিতে বলেন সেনেটের দুই শীর্ষ নেতা।\n" +
                "\n" +
                "মার্চের পর মার্কিন আইন প্রণেতাদের সামনে টিকটক সিইও শউ জি চিউ’র পুনরায় সাক্ষ্য দেওয়ার ঘটনা হতে যাচ্ছে এটি। সে সময় চীনা মালিকানাধীন কোম্পানিটির প্রধানকে বিভিন্ন প্রশ্নবাণে বিদ্ধ করেছিলেন সেনেট সদস্যরা। এর মধ্যে অ্যাপটি শিশুদের মানসিক স্বাস্থ্যের ক্ষতি করছে, এমন অভিযোগও ছিল।\n" +
                "\n" +
                "মার্কিন কংগ্রেসের প্রস্তাবিত আইন অনুসারে, এতে বাইডেন প্রশাসনকে নতুন ক্ষমতা দেওয়া হবে, যার মাধ্যমে টিকটকের মতো বিদেশী সামাজিক মাধ্যমে মার্কিন ব্যবহারকারীদের প্রবেশাধিকার বন্ধ হয়ে যাওয়ার ঝুঁকি রয়েছে। যুক্তরাষ্ট্রে অ্যাপটির ব্যবহারকারী সংখ্যা ১৫ কোটির বেশি।\n" +
                "\n" +
                "ডার্বিন ও গ্রেহাম বলেন, এ শুনানির মাধ্যমে অনলাইনে শিশুদের নিরাপত্তা দেওয়ার ক্ষেত্রে ব্যর্থ হওয়ার বিষয়টি নিয়ে শীর্ষ কোম্পানিগুলোর সিইও’দেরকে চাপ দেওয়ার সুযোগ পাবেন কমিটির সদস্যরা।\n" +
                "\n" +
                "এ শুনানিতে সাক্ষ্য দেবেন ফেইসবুক ও ইনস্টাগ্রামের মালিক কোম্পানি মেটা’র সিইও মার্ক জাকারবার্গ, এক্স-এর সিইও লিন্ডা ইয়াকারিনো, স্ন্যাপের সিইও ইভান স্পিগেল ও ডিসকর্ডের সিইও জেসন সাইট্রন।\n" +
                "\n" +
                "“শীর্ষ প্রযুক্তি কোম্পানিগুলো যে অনলাইনে নিজেদের ও শিশুদের নিরাপত্তা প্রদানে ব্যর্থ হয়েছে, সে প্রশ্নের জবাব দিতে হবে।” --বলেন ডার্বিন ও গ্রেহাম।\n" +
                "\n" +
                "এ বছর বেশ কয়েকটি বিলে অনুমোদন দিয়েছে সেনেট কমিটি। এর একটি হল, অনলাইনে শিশুদের যৌন নিপিড়ণ বিষয়ক কনটেন্টে প্রযুক্তি কোম্পানিগুলো যে নাগরিক ও অপরাধ আইনের আওতামুক্ত ছিল, সে সুবিধা সরিয়ে ফেলা। বিলটি উত্থাপিত হয়েছিল ২০২০ সালে।\n" +
                "\n" +
                "এর পাশাপাশি, অনলাইনে শিশুদের ওপর যৌন নিপিড়ণ ঠেকাতে জাতীয় কমিশন গঠন এবং এমন অপরাধের তদন্ত ও বিচার ব্যবস্থা আধুনিকায়নের মতো বিষয়গুলোও উল্লেখ করা হয়েছে আরও দুটি বিলে।");
        arrayList.add(hashMap);






    }




    //-------------------------------

    //////////////----------------------

}