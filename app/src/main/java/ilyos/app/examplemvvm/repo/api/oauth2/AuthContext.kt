package ilyos.app.examplemvvm.repo.api.oauth2

import com.google.gson.annotations.SerializedName
import java.io.Serializable

open class  AuthContext(
    @SerializedName("user") var user: User,
    @SerializedName("group") var group: String
) : Serializable {
    fun clone(context: AuthContext) {
        user.clone(context.user)
        this.group = context.group
    }
}