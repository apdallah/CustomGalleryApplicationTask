package com.apdallahyousry.customgalleryapplication.utils

import androidx.fragment.app.Fragment


fun Fragment.getDisplaySpanCount():Int{
    val marginPixles=16*(resources.displayMetrics.densityDpi/106)
    val itemPixles=110*((resources.displayMetrics.densityDpi+marginPixles)/160)
    return resources.displayMetrics.widthPixels/itemPixles
}