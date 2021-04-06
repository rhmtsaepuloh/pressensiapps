package com.folkatech.pressensiapps.ui.fragment.beranda

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.folkatech.pressensi.model.Informasi
import com.folkatech.pressensiapps.R
import com.folkatech.pressensiapps.common.base.BaseAdapter
import com.folkatech.pressensiapps.common.base.BaseFragment
import com.folkatech.pressensiapps.ui.fragment.beranda.adapter.OnPengumumanItemClickListener
import com.folkatech.pressensiapps.ui.fragment.beranda.adapter.PengumumanAdapter
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator
import kotlinx.android.synthetic.main.fragment_beranda.*
import kotlinx.android.synthetic.main.loading_layout.*
import java.lang.Exception
import javax.inject.Inject

class BerandaFragment : BaseFragment(), BerandaContract.View, BaseAdapter.OnReloadClickListener {
    private val TAG = "BerandaFragment"

    @Inject
    lateinit var presenter: BerandaContract.Presenter

    private val PAGE_SIZE = 15
    private var currentPage = 1
    private var isLastPage = false
    private var isLoading = false
    private lateinit var linearLayoutManager: LinearLayoutManager;
    private var pengumumanAdapter: PengumumanAdapter = PengumumanAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fragmentComponent.inject(this)
        presenter.attach(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        val listNews: View = inflater.inflate(R.layout.fragment_beranda, container, false)
        return listNews
    }

    override fun onInit() {
        initView();
        presenter.getPengumuman(currentPage);
    }

    override fun initView() {
        try {
            linearLayoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            rv_notification.layoutManager = linearLayoutManager;

            pengumumanAdapter = PengumumanAdapter()
            pengumumanAdapter.setOnReloadClickListener(this);

            pengumumanAdapter.setOnItemClickListener(object : OnPengumumanItemClickListener {
                override fun onClickItem(view: View, obj: Informasi, position: Int) {
//                val intent = Intent(context, PengumumanDetailActivity::class.java)
//                intent.putExtra("id_pengumuman", obj.idPengumuman)
//                Log.d("TESAJA", " "+obj.idPengumuman)
//                startActivity(intent)
                }
            })

            rv_notification.itemAnimator = SlideInDownAnimator()
            rv_notification.adapter = pengumumanAdapter

            rv_notification.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val visibleItemCount = linearLayoutManager.childCount
                    val totalItemCount = linearLayoutManager.itemCount
                    val firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()

                    if (!isLoading && !isLastPage) {
                        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE
                        ) {
                            isLoading = true
                            currentPage++
                            presenter.getPengumuman(currentPage)
                        }
                    }
                }
            })
        } catch (e: Exception) {
            Log.d(TAG, "initView: Error lm")
        }
    }

    override fun initbottomSheet() {
        TODO("Not yet implemented")
    }

    override fun onClick() {
        TODO("Not yet implemented")
    }

    override fun setText() {
        TODO("Not yet implemented")
    }

    override fun initCurrentDate() {
        TODO("Not yet implemented")
    }

    override fun initBgBeranda() {
        TODO("Not yet implemented")
    }

    override fun onSuccessGetPengumuman(data: List<Informasi>) {
        if (currentPage == 1) {
            loading_timeline.visibility = View.GONE
            pengumumanAdapter.clear()
        } else
            pengumumanAdapter.removeFooter()

        isLoading = false

        if (data.isNotEmpty()) {
            pengumumanAdapter.addAll(data)
            pengumumanAdapter.notifyDataSetChanged()
        } else {
            tv_not_found_notif.visibility = View.VISIBLE
        }

        if (data.size >= PAGE_SIZE)
            pengumumanAdapter.addFooter()
        else
            isLastPage = true
    }

    override fun onErrorGetPengumuman(message: String) {
        loading_timeline.visibility = View.GONE
        tv_not_found_notif.visibility = View.VISIBLE
        isLoading = false
    }

    override fun onReloadClick() {
        TODO("Not yet implemented")
    }
}