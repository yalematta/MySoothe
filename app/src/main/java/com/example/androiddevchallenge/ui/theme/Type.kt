/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.theme

import android.text.method.TextKeyListener
import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.intl.LocaleList
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.R

// Set of Material typography styles to start with

val kulimParkFamily = FontFamily(
    Font(resId = R.font.kulim_park_light, weight = FontWeight.Light),
    Font(resId = R.font.kulim_park_regular, weight = FontWeight.Normal)
)

val latoFamily = FontFamily(
    Font(resId = R.font.lato_bold, weight = FontWeight.Bold),
    Font(resId = R.font.lato_regular, weight = FontWeight.Normal)
)

val typography = Typography(

    h1 = TextStyle(
        fontFamily = kulimParkFamily,
        fontWeight = FontWeight.Light,
        fontSize = 28.sp,
        letterSpacing = 1.15.sp
    ),

    h2 = TextStyle(
        fontFamily = kulimParkFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 15.sp,
        letterSpacing = 1.15.sp,
        localeList = LocaleList(TextKeyListener.Capitalize.CHARACTERS.toString())
    ),

    h3 = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),

    body1 = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        letterSpacing = 0.sp
    ),

    button = TextStyle(
        fontFamily = latoFamily,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        letterSpacing = 1.15.sp,
        localeList = LocaleList(TextKeyListener.Capitalize.CHARACTERS.toString())
    ),

    caption = TextStyle(
        fontFamily = kulimParkFamily,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        letterSpacing = 1.15.sp,
        localeList = LocaleList(TextKeyListener.Capitalize.CHARACTERS.toString())
    )

)
