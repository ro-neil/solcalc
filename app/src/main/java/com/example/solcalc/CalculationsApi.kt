package com.example.solcalc

import kotlin.math.ceil

class CalculationsApi {

    val exchangeRate = 153.1

    fun getkWhPerDay(jpsMonthly: Double): Double {

        var kWhPerMonth: Double = jpsMonthly / 57

        return kWhPerMonth / 30

    }

    fun getNumberOfPanels(kWhPerDay: Double): Int {

        return ceil(kWhPerDay / 2.21).toInt()

    }


    fun getArraySize(numberOfPanels: Int): Double {
        return numberOfPanels * 500.0
    }

    fun getInverterCapacity(arraySize: Double): Double {

        return if (arraySize <= 3000.0) {
            3000.0
        } else {
            arraySize
        }

    }

    fun getCostOfInverter(inverterCapacity: Double): Double {
        return ((inverterCapacity * 221.0) / 1000) * exchangeRate
    }

    fun getCostOfPanels(numberOfPanels: Int): Double {
        return (numberOfPanels * 282.0) * exchangeRate
    }

    fun getStorageCapacity(kWhPerDay: Double): Double {

        var amount = kWhPerDay * .65
        return if (amount <= 5.0) {
            5.0
        } else {
            amount
        }

    }


    fun getCostOfBatteries(storageCapacity: Double): Double {
        return (storageCapacity * 301.7) * exchangeRate
    }

    fun getCostOfLabor(arraySize: Double): Double {
        return if (arraySize <= 8000) {
            90000.00
        } else {
            arraySize * 82.78
        }
    }

    fun getkWhPerDayWithPercentageCut(jpsMonthly: Double, percenatageToCut: Double): Double {

        var kWhPerMonth = jpsMonthly / 57

        var kWhPerDay = kWhPerMonth / 30

        return kWhPerDay * percenatageToCut

    }

    fun calculatePayBackPeriod(totalInstallationCost: Double, jpsMonthly: Double): Double {

        var annualJPSPayments = (jpsMonthly * 12) * 1.0

        return totalInstallationCost / annualJPSPayments

    }

    fun getCostOfMaterial(arraySize: Double): Double {
        return if (arraySize <= 8000) {
            96000.00
        } else {
            arraySize * 63.58
        }
    }

    fun getCostOfService(arraySize: Double): Double {

        return arraySize * 80.58
    }

    fun getTotalSystemCostWithBatteries(jpsBill: Double, percentageToCut: Double): SolarQuote {

        var kWhPerDay = getkWhPerDayWithPercentageCut(jpsBill, percentageToCut)

        var numberOfPanels = getNumberOfPanels(kWhPerDay)

        var arraySize = getArraySize(numberOfPanels)

        var inverterCapacity = getInverterCapacity(arraySize)

        var storageCapacity = getStorageCapacity(kWhPerDay)

        var costOfInverter = getCostOfInverter(inverterCapacity)

        var costOfBatteries = getCostOfBatteries(storageCapacity)

        var costOfPanels = getCostOfPanels(numberOfPanels)

        var costOfMaterial = getCostOfMaterial(arraySize)

        var costOfLabor = getCostOfLabor(arraySize)

        var costOfService = getCostOfService(arraySize)

        var totalInstallationCost =
            costOfBatteries + costOfPanels + costOfLabor + costOfMaterial + costOfInverter + costOfService

        var payBackPeriod = calculatePayBackPeriod(totalInstallationCost, jpsBill)

        val quote = SolarQuote()
        quote.numberOfPanels = numberOfPanels
        quote.arraySize = arraySize
        quote.inverterCapacity = inverterCapacity
        quote.storageCapacity = storageCapacity
        quote.costOfInverter = costOfInverter
        quote.costOfBatteries = costOfBatteries
        quote.costOfLabor = costOfLabor
        quote.costOfMaterial = costOfMaterial
        quote.costOfPanels = costOfPanels
        quote.costOfService = costOfService
        quote.totalInstallationCost = totalInstallationCost
        quote.payBackPeriod = payBackPeriod

        return quote
    }

    fun getTotalSystemCostWithoutBatteries(jpsBill: Double, percentageToCut: Double): SolarQuote {

        var kWhPerDay = getkWhPerDayWithPercentageCut(jpsBill, percentageToCut)

        var numberOfPanels = getNumberOfPanels(kWhPerDay)

        var arraySize = getArraySize(numberOfPanels)

        var inverterCapacity = getInverterCapacity(arraySize)

        var storageCapacity = getStorageCapacity(kWhPerDay)

        var costOfInverter = getCostOfInverter(inverterCapacity)

        var costOfPanels = getCostOfPanels(numberOfPanels)

        var costOfMaterial = getCostOfMaterial(arraySize)

        var costOfLabor = getCostOfLabor(arraySize)

        var costOfService = getCostOfService(arraySize)

        var totalInstallationCost =
            costOfPanels + costOfLabor + costOfMaterial + costOfInverter + costOfService

        var payBackPeriod = calculatePayBackPeriod(totalInstallationCost, jpsBill)

        val quote = SolarQuote()
        quote.kWhPerDay = kWhPerDay
        quote.numberOfPanels = numberOfPanels
        quote.arraySize = arraySize
        quote.inverterCapacity = inverterCapacity
        quote.storageCapacity = storageCapacity
        quote.costOfInverter = costOfInverter

        quote.costOfLabor = costOfLabor
        quote.costOfMaterial = costOfMaterial
        quote.costOfPanels = costOfPanels
        quote.costOfService = costOfService
        quote.totalInstallationCost = totalInstallationCost
        quote.payBackPeriod = payBackPeriod

        return quote
    }

    fun getTotalSystemCost(
        jpsBill: Double,
        percentageToCut: Double,
        batteries: Boolean
    ): SolarQuote {

        return if (batteries) {
            getTotalSystemCostWithBatteries(jpsBill, percentageToCut)
        } else {
            getTotalSystemCostWithoutBatteries(jpsBill, percentageToCut)
        }

    }


    class SolarQuote {
        var kWhPerDay: Double = 0.0

        var numberOfPanels: Int = 0

        var arraySize: Double = 0.0

        var inverterCapacity: Double = 0.0

        var storageCapacity: Double = 0.0

        var costOfInverter: Double = 0.0

        var costOfBatteries: Double = 0.0

        var costOfPanels: Double = 0.0

        var costOfMaterial: Double = 0.0

        var costOfLabor: Double = 0.0

        var costOfService: Double = 0.0

        var totalInstallationCost: Double = 0.0

        var payBackPeriod: Double = 0.0

    }
}

//fun main(args: Array<String>) {
//val calc = SolCalc()
//println(calc.GetTotalSystemCost(45000.0,.90,true).totalInstallationCost)

//}
