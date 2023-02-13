package com.aymenjegham.framework.global.helper



sealed class Navigation {


    data class Back(val ShouldFinish: Boolean) : Navigation()

    data class NavigationDetails(val string: String) : Navigation()


}