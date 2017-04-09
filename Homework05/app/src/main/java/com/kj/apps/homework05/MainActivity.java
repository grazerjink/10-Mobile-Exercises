package com.kj.apps.homework05;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    FeedsAdapter feedsAdapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
        if (kiemTraKetNoiInternet())
            getTask("http://feeds.feedburner.com/elise/simplyrecipes");
        else
            Toast.makeText(MainActivity.this, "Thiết bị chưa kết nối Internet", Toast.LENGTH_LONG).show();
    }

    public boolean kiemTraKetNoiInternet() {
        ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null) {
            return false;
        } else {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void addEvents() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (kiemTraKetNoiInternet()) {
                    swipeRefreshLayout.setRefreshing(true);
                    (new Handler()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(false);
                            getTask("http://feeds.feedburner.com/elise/simplyrecipes");
                        }
                    }, 3000);
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                    Toast.makeText(MainActivity.this, "Thiết bị chưa kết nối Internet", Toast.LENGTH_LONG).show();
                }
            }

        });
    }

    void addControls() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE);
    }

    void getTask(String path) {
        try {
            new MyAsyncTask().execute(new URL(path));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    void initRecyclerView(List<Item> feeds) {
        if (feedsAdapter == null) {
            feedsAdapter = new FeedsAdapter(this, R.layout.item_list, feeds);
        } else {
            feedsAdapter.clear();
            feedsAdapter.addAll(feeds);
            feedsAdapter.notifyDataSetChanged();
            swipeRefreshLayout.setRefreshing(false);
        }
        recyclerView.setAdapter(feedsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    class MyAsyncTask extends AsyncTask<URL, Void, List<Item>> {

        @Override
        protected List<Item> doInBackground(URL... urls) {
            URLConnection urlConnection = null;
            List<Item> itemList = new ArrayList<>();
            try {
                urlConnection = urls[0].openConnection();
                HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
                int respCode = httpURLConnection.getResponseCode();
                if (respCode == HttpURLConnection.HTTP_OK) {
                    InputStream is = httpURLConnection.getInputStream();
                    itemList = parseRSSFeeds(is);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return itemList;
        }


        private List<Item> parseRSSFeeds(InputStream is) {
            List<Item> list = new ArrayList<>();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            try {
                DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
                Document document = documentBuilder.parse(is);
                if (document != null) {
                    NodeList itemList = document.getElementsByTagName("item");
                    if (itemList != null) {
                        for (int i = 0; i < itemList.getLength(); i++) {
                            Node item = itemList.item(i);
                            Item feed = new Item();
                            NodeList childrenItems = item.getChildNodes();
                            for (int j = 0; j < childrenItems.getLength(); j++) {
                                Node child = childrenItems.item(j);
                                String contents = child.getTextContent();
                                switch (child.getNodeName()) {
                                    case Item.TITLE:
                                        feed.setTitle(contents);
                                        break;
                                    case Item.DESCRIPTION:
                                        String temp[] = contents.split("/>");
                                        feed.setContent(Html.fromHtml(temp[1].split("<a")[0].trim()).toString());
                                        feed.setLink_img(temp[0].split("src=\"")[1].split("\"")[0].trim());
                                        break;
                                    case Item.CREATOR:
                                        feed.setCreator(contents);
                                        break;
                                    case Item.PUB_DATE:
                                        feed.setDate(contents);
                                        break;
                                    case Item.LINK:
                                        feed.setLink(contents);
                                        break;
                                }
                            }
                            list.add(feed);
                        }
                    }
                }
            } catch (ParserConfigurationException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return list;
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            super.onPostExecute(items);
            initRecyclerView(items);
        }
    }
}
