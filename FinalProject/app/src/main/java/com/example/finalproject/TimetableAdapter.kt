package com.example.finalproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


// TimetableAdapter：負責 70 格課表的內容（目前先顯示節數與星期，可之後綁資料）
class TimetableAdapter(private val totalCells: Int) : RecyclerView.Adapter<TimetableAdapter.CellViewHolder>() {


    // 每一格的 ViewHolder
    class CellViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cellText: TextView = itemView.findViewById(R.id.cellText)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CellViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemtablecell, parent, false)
        return CellViewHolder(view)
    }


    override fun getItemCount(): Int = totalCells


    override fun onBindViewHolder(holder: CellViewHolder, position: Int) {
// 計算星期與節數（僅示範）
        val column = position % 5 // 0~4 對應 星期一~五
        val row = position / 5 // 第幾節課（0~13 對應 1~14 節）


        holder.cellText.text = "週${column + 1}\n第${row + 1}節"

// 未來在這裡塞課程資料（例如課名、教室、老師）
// 可以改成讀資料庫或 ViewModel 傳入的資料
    }
}


//class TimetableAdapter(private var data: List<String>) :
//    RecyclerView.Adapter<TimetableAdapter.ViewHolder>() {
//
//    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val tvCourse: TextView = view.findViewById(R.id.tvCourseName)
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.itemtablecell, parent, false)
//        return ViewHolder(view)
//    }
//
//    override fun getItemCount(): Int = data.size
//
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        holder.tvCourse.text = data[position]
//        // 可以在這裡加格子背景顏色，或跨節次合併
//    }
//}
