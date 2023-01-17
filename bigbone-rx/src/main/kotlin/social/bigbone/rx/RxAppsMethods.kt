package social.bigbone.rx

import io.reactivex.rxjava3.core.Single
import social.bigbone.MastodonClient
import social.bigbone.api.Scope
import social.bigbone.api.entity.auth.AppRegistration
import social.bigbone.api.method.AppsMethods
import social.bigbone.rx.extensions.onErrorIfNotDisposed

class RxAppsMethods(client: MastodonClient) {
    private val appsMethods = AppsMethods(client)

    fun createApp(
        clientName: String,
        redirectUris: String = "urn:ietf:wg:oauth:2.0:oob",
        scope: Scope,
        website: String? = null
    ): Single<AppRegistration> {
        return Single.create {
            try {
                val registration = appsMethods.createApp(clientName, redirectUris, scope, website)
                it.onSuccess(registration.execute())
            } catch (throwable: Throwable) {
                it.onErrorIfNotDisposed(throwable)
            }
        }
    }
}