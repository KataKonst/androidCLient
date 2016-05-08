package androidinterview.com.customlistviewimagetext

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.katakonst.licenta.AsyncTask.users.DownloadImageTask
import com.example.katakonst.licenta.Constants
import com.example.katakonst.licenta.JsonModels.Tracks
import java.util.*

class TracksAdapter(private val context: Activity, private val itemname: ArrayList<Tracks>?)// TODO Auto-generated constructor stub
: ArrayAdapter<Tracks>(context,  com.example.katakonst.licenta.R.layout.track_list, itemname) {
    var map = hashMapOf("one" to true, "two" to true, "three" to true)
    val views=arrayListOf<View>()


    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate( com.example.katakonst.licenta.R.layout.track_list, null, true)

       if(map.containsKey(itemname!!.get(position).name)==false) {
           ( rowView.findViewById(com.example.katakonst.licenta.R.id.Itemname) as TextView).text = itemname.get(position).name
           val imageTask = DownloadImageTask(( rowView.findViewById(com.example.katakonst.licenta.R.id.TrackIcon) as ImageView ), Constants.streamip + "/" + itemname.get(position).photoLink)
           imageTask.execute()
           views.add(rowView)
           map.put(itemname.get(position).name,true);

       }

        if(position>=views.size)
            return  views.get(views.size-1);
        else
            return views[position]
    }
}