package com.example.fragmentrecycleview

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment(), RecyclerViewAdapter.Clicklistener {
    private lateinit var adapter: RecyclerViewAdapter
    val listData: ArrayList<DataModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_main, container, false)
        iItemsList()
        initRecyclerView(view)
        return view
    }

    private fun initRecyclerView(view: View){
        var recyclerview = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerview.layoutManager = LinearLayoutManager(activity)
        adapter = RecyclerViewAdapter(listData, this)
        recyclerview.adapter = adapter
    }

    private fun iItemsList() {
        listData.add(DataModel(R.drawable.angular, "Angular", "Web Application"))
        listData.add(DataModel(R.drawable.cp, "C Programming", "Embed Programming"))
        listData.add(DataModel(R.drawable.cpp, "C++ Programming", "Embed Programming"))
        listData.add(DataModel(R.drawable.dotnet, ".NET Programming", "Desktop and Web Programming"))
        listData.add(DataModel(R.drawable.java, "Java Programming", "Desktop and Web Programming"))
        listData.add(DataModel(R.drawable.magento, "Magento", "Web Application Framework"))
        listData.add(DataModel(R.drawable.nodejs, "NodeJS", "Web Application Framework"))
        listData.add(DataModel(R.drawable.python, "Python", "Desktop and Web Programming"))
        listData.add(DataModel(R.drawable.shopify, "Shopify", "E-Commerce Framework"))
        listData.add(DataModel(R.drawable.wordpress, "Wordpress", "WebApplication Framewrok"))
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            MainFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    override fun onItemClick(dataModel: DataModel) {
        val transaction = activity?.supportFragmentManager!!.beginTransaction()
        val orientation = this.resources.configuration.orientation
        if (orientation != Configuration.ORIENTATION_LANDSCAPE) {
            transaction.hide(activity?.supportFragmentManager!!.findFragmentByTag("main_fragment")!!)
        }
        var fragment: Fragment = DetailFragment.newInstance(dataModel.itemName!!, dataModel.itemDescription!!, dataModel.image)
        transaction.replace(R.id.frame_details, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}