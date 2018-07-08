package samplemvvm.presentation.house

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_house.*
import samplemvvm.R
import samplemvvm.MainApplication
import samplemvvm.di.components.DaggerViewModelComponent
import samplemvvm.di.components.ViewModelComponent
import samplemvvm.di.modules.ViewModelModule
import samplemvvm.extension.add
import javax.inject.Inject

class HouseActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: HouseViewModel
    private lateinit var component: ViewModelComponent
    private val disposeBag by lazy { CompositeDisposable() }
    private lateinit var adapter: HouseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_house)

        component = DaggerViewModelComponent
                .builder().appComponent(MainApplication.appComponent)
                .viewModelModule(ViewModelModule())
                .build()
        component.inject(this)

        adapter = HouseAdapter()
        house_recycleview.layoutManager = LinearLayoutManager(this)
        house_recycleview.adapter = adapter

        prepareOutput()
        prepareInput()
    }

    override fun onDestroy() {
        super.onDestroy()
        disposeBag.clear()
    }

    private fun prepareInput() {
        viewModel.input.loadData.onNext(Unit)
    }

    private fun prepareOutput() {

        viewModel.output.houseList
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    adapter.houseList.clear()
                    adapter.houseList.addAll(it)
                    adapter.notifyDataSetChanged()
                }, { error ->
                    Toast.makeText(this, error.localizedMessage, Toast.LENGTH_SHORT).show()
                })
                .add(disposeBag)
    }
}