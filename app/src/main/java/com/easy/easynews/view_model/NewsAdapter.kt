package com.easy.easynews.view_model

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.easy.easynews.R
import com.easy.easynews.modal.NewsPojo
import com.easy.easynews.view.WebViewActivity

class NewsAdapter(private val context: Context, private val newsArrayList: ArrayList<NewsPojo>) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.news_raw_item, parent, false);
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentNews = newsArrayList[position]
        holder.title.text = currentNews.title
        holder.description.text = currentNews.description

        Glide.with(context)
            .load(currentNews.urlToImage)
            .centerCrop().into(holder.image)

        holder.cardItem.setOnClickListener {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("url", currentNews.url)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return newsArrayList.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.news_image)
        val title: TextView = view.findViewById(R.id.news_title)
        val description: TextView = view.findViewById(R.id.new_description)
        val cardItem: CardView = view.findViewById(R.id.news_cardItem)
    }
}