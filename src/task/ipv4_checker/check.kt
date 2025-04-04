package task.ipv4_checker



fun isValidIPv4(address: String): Boolean {
   return false

}
fun main() {


    //  عناوين IPv4 غير صحيحة
    check("Test 5 - Out of range", isValidIPv4("256.100.50.25"), false) // 256 غير صالح
    check("Test 6 - Missing segment", isValidIPv4("192.168.1"), false) // أقل من 4 مقاطع
    check("Test 7 - Extra segment", isValidIPv4("192.168.1.1.1"), false) // أكثر من 4 مقاطع
    check("Test 8 - Leading zero", isValidIPv4("192.168.01.1"), false) // صفر بادئ غير مسموح
    check("Test 9 - Contains letters", isValidIPv4("192.168.1.a"), false) // يحتوي على حرف
    check("Test 10 - Negative number", isValidIPv4("192.168.1.-1"), false) // رقم سلبي
    check("Test 11 - Contains spaces", isValidIPv4("192.168. 1.1"), false) // يحتوي على مسافات
    check("Test 12 - Double dots", isValidIPv4("192..168.1.1"), false) // نقطتان متتاليتان
    check("Test 13 - Double dots", isValidIPv4("-192.168.1.1"), false) // يحتوي على رموز
    check("Test 14 - Valid IPv4", isValidIPv4("A.0.0.0"), false)// يحتوي على نص

}
fun check(name: String, result: Boolean, correctResult: Boolean) {
    if (result == correctResult) {
        println("Success - $name")
    } else {
        println("Failed - $name")
    }
}
