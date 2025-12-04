package com.example.finalproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TimetableFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TimetableFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

//    private lateinit var recyclerView: RecyclerView
//    private lateinit var adapter: TimetableAdapter




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_timetable, container, false)
    }



//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        recyclerView = view.findViewById(R.id.timetableRecyclerView)
//
//        // 設定 Grid: Mon~Fri (5欄)
//        recyclerView.layoutManager = GridLayoutManager(requireContext(), 5)
//
//        // 40 格：5天 × 8節
//        val emptyData = List(40) { "" }
//
//        adapter = TimetableAdapter(emptyData)
//        recyclerView.adapter = adapter
//    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TimetableFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TimetableFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

/*


/ 上方星期列的水平滑動區域
private lateinit var headerScroll: HorizontalScrollView


    // 課表內容（RecyclerView 外層）的水平滑動區域
    private lateinit var contentScroll: HorizontalScrollView


    // 整個課表的垂直滑動區域
    private lateinit var verticalScroll: ScrollView


    // 課表 RecyclerView（用 5 欄 Grid 來排星期一～五）
    private lateinit var recyclerView: RecyclerView


override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    // 綁定 XML 元件（此時 View 已完全建立，適合綁定）
        headerScroll = view.findViewById(R.id.headerScroll)
        contentScroll = view.findViewById(R.id.contentScroll)
        verticalScroll = view.findViewById(R.id.verticalScroll)
        recyclerView = view.findViewById(R.id.scheduleRecyclerView)


    // 設定 RecyclerView（課表格子）
        setupRecyclerView()


    // 設定「星期列」與「內容」的水平滑動同步
        syncScroll()
    }

private fun setupRecyclerView() {
    // GridLayoutManager 的 spanCount = 5 → 星期一到星期五
        recyclerView.layoutManager = GridLayoutManager(requireContext(), 5)


    // 5 天 × 14 節課 = 70 格
        val totalCells = 5 * 14


    // 將 70 格丟給 Adapter，之後可放課程資料
        recyclerView.adapter = TimetableAdapter(totalCells)
    }

    private fun syncScroll() {
    // 當 headerScroll（星期列）移動時，讓 contentScroll 也跟著移動
        headerScroll.viewTreeObserver.addOnScrollChangedListener {
            contentScroll.scrollTo(headerScroll.scrollX, 0)
        }


    // 當內容滑動時，讓上面的星期列跟著同步
        contentScroll.viewTreeObserver.addOnScrollChangedListener {
            headerScroll.scrollTo(contentScroll.scrollX, 0)
        }
    }

* */