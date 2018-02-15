package samplemvvm.extensions

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

inline fun Disposable.add(compositeDisposable: CompositeDisposable): Boolean = compositeDisposable.add(this)