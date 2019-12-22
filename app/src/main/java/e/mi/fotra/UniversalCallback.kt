package e.mi.fotra

interface UniversalCallback<T> {
    fun onSuccess(value: T)

    fun onFailure(e: Throwable? = null)
}