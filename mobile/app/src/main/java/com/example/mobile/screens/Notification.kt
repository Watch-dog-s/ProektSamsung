package com.example.mobile.screens


enum class A { Good, Norm, Bad }  // это надо для домашнего экрана там в зависимости от значения будут разные иконки типо получил 2 - красная 5 - зелёная
enum class B { Visit, Mark, Report }

data class Notification (
    val a:A,
    val b:B
)