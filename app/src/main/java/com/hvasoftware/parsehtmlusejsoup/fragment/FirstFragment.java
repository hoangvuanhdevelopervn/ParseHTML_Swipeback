package com.hvasoftware.parsehtmlusejsoup.fragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hvasoftware.parsehtmlusejsoup.AdapterData;
import com.hvasoftware.parsehtmlusejsoup.Main2Activity;
import com.hvasoftware.parsehtmlusejsoup.Quote;
import com.hvasoftware.parsehtmlusejsoup.R;
import com.hvasoftware.parsehtmlusejsoup.swipeback.BaseSwipeBackFragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FirstFragment extends BaseSwipeBackFragment {
    private static final String TAG = "FirstFragment";
    private RecyclerView recyclerView;
    private List<Quote> quoteList = new ArrayList<>();
    private ProgressBar progressBar;


    public static FirstFragment newInstance() {
        Bundle args = new Bundle();
        FirstFragment fragment = new FirstFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        setUpView(view);
        return attachToSwipeBack(view);
    }

    private boolean setUpView(View view) {
        recyclerView = view.findViewById(R.id.rvData);
        progressBar = view.findViewById(R.id.progress_circular);

        view.findViewById(R.id.btnOpenNewFragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAddFragmentListener != null) {
                    mAddFragmentListener.onAddFragment(FirstFragment.newInstance(),
                            SecondFragment.newInstance(), R.id.container);
                }
            }
        });

        view.findViewById(R.id.btnOpenNewActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Main2Activity.class);
                startActivity(intent);
            }
        });


        view.findViewById(R.id.btnParseData).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ParseQuoteHTML(1).execute();
                progressBar.setVisibility(View.VISIBLE);
                quoteList.clear();
            }
        });
        return false;
    }

    private class ParseQuoteHTML extends AsyncTask<Void, Void, Void> {

        private int page = 1;

        public ParseQuoteHTML(int page) {
            this.page = page;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Document document = null;
            try {
                String url = "https://www.azquotes.com/top_quotes.html?p=" + page;
                document = Jsoup.connect(url).get();
                Elements quoteElement = document.select("div.wrap-block");


                for (Element element : quoteElement) {
                    String quoteContent = element.select("a.title").text();
                    String author = element.select("div.author a").text();
                    String tags = element.select("div.tags").text();
                    String[] listCategory = tags.split(",");

                    Quote quote = new Quote(quoteContent, author, tags);
                    quoteList.add(quote);

                    Log.wtf(TAG, "quoteContent: " + quoteContent);
                    Log.wtf(TAG, "author: " + author);
                    Log.wtf(TAG, "tags: " + tags);
                    Log.wtf(TAG, "-----------------------------------------------------------------------------");

                }


            } catch (IOException e) {
                Log.wtf(TAG, e.getMessage());
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.GONE);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
            recyclerView.setLayoutManager(linearLayoutManager);
            AdapterData adapterData = new AdapterData(getContext());
            recyclerView.setAdapter(adapterData);
            adapterData.setQuoteList(quoteList);
            adapterData.notifyDataSetChanged();
        }
    }

}
