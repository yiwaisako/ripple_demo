package jp.yiwaisako.ripple_demo

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.RippleDrawable
import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.yqritc.recyclerviewflexibledivider.FlexibleDividerDecoration
import java.util.*

class BookListAdapter(private val context: Context) : RecyclerView.Adapter<BookListAdapter.ViewHolder>(),
        FlexibleDividerDecoration.ColorProvider,
        FlexibleDividerDecoration.VisibilityProvider {

    private val dividerColor: Int = ContextCompat.getColor(context, R.color.material_grey_600)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val rippleColors = intArrayOf(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark,
                    R.color.medium_turquoise, R.color.eclipse, R.color.malibu
            )

            val i = Random().nextInt(rippleColors.size)
            val mask = ContextCompat.getDrawable(context, R.drawable.mask)
            val color = ContextCompat.getColor(context, rippleColors[i])
            holder.root.foreground = RippleDrawable(ColorStateList.valueOf(color), null, mask).mutate()
        }

        holder.title.text = "title_$position"
        Glide.with(context)
                .load("http://placehold.jp/24/cc9999/993333/150x119.png")
                .into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun dividerColor(position: Int, parent: RecyclerView?): Int {
        return dividerColor
    }

    override fun shouldHideDivider(position: Int, parent: RecyclerView?): Boolean {
        return false
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val root = view.findViewById(R.id.root) as CardView
        val title = view.findViewById(R.id.title_text) as TextView
        val thumbnail = view.findViewById(R.id.thumbnail_image) as ImageView
        val readButtonWrapper = view.findViewById(R.id.read_button_wrapper_relative) as CardView
        val trialReadingWrapper = view.findViewById(R.id.trial_reading_wrapper_relative) as CardView
    }
}