package com.fionasiregar0032.taxflow.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    val options = listOf("PPN", "PPh")
    var selectOption by remember { mutableStateOf<String?>(null) }

    var nama by remember { mutableStateOf("") }
    var jumlahTanggungan by remember { mutableStateOf("") }
    var totalPenghasilan by remember { mutableStateOf("") }
    var iuranJHT by remember { mutableStateOf("0") }
    var iuranBPJS by remember { mutableStateOf("0") }
    var iuranZakat by remember { mutableStateOf("0") }

    val pekerjaanOptions = listOf("Pegawai Tetap", "Non-Pegawai")
    var selectedPekerjaan by remember { mutableStateOf(pekerjaanOptions[0]) }
    var pekerjanExpanded by remember { mutableStateOf(false) }

    var penghasilanNeto by remember { mutableStateOf(0.0) }
    var penghasilanKenaPajak by remember { mutableStateOf(0.0) }
    var pajak by remember { mutableStateOf(0.0) }
    var hitung by remember { mutableStateOf(false) }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Hitung Pajak", fontWeight = FontWeight.Bold)
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFF90EE90),
                    titleContentColor = Color.Black,
                )
            )
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp)
                .imePadding()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Spacer(modifier = Modifier.height(8.dp))

            if (selectOption == null) {

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    TextField(
                        value = "Pilih Jenis Pajak",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Jenis Pajak") },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                        },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        options.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(item) },
                                onClick = {
                                    selectOption = item
                                    expanded = false
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF90EE90))// Hijau
                ) {
                    Text("kembali", color = Color.White)
                }
            } else {

                if (selectOption == "PPh") {
                    OutlinedTextField(
                        value = nama,
                        onValueChange = { nama = it },
                        label = { Text("Nama") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = jumlahTanggungan,
                        onValueChange = { jumlahTanggungan = it },
                        label = { Text("Jumlah Tanggungan") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = totalPenghasilan,
                        onValueChange = { totalPenghasilan = it },
                        label = { Text("Total Penghasilan") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = iuranJHT,
                        onValueChange = { iuranJHT = it },
                        label = { Text("Iuran JHT (2%)") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = iuranBPJS,
                        onValueChange = { iuranBPJS = it },
                        label = { Text("Iuran BPJS (1%)") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = iuranZakat,
                        onValueChange = { iuranZakat = it },
                        label = { Text("Iuran Zakat (1%)") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    Text("Status", fontWeight = FontWeight.Bold)

                    ExposedDropdownMenuBox(
                        expanded = pekerjanExpanded,
                        onExpandedChange = { pekerjanExpanded = !pekerjanExpanded }
                    ) {
                        TextField(
                            value = selectedPekerjaan,
                            onValueChange = {},
                            readOnly = true,
                            label = { Text("Pilih Pekerjaan") },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(pekerjanExpanded)
                            },
                            modifier = Modifier.menuAnchor().fillMaxWidth()
                        )
                        ExposedDropdownMenu(
                            expanded = pekerjanExpanded,
                            onDismissRequest = { pekerjanExpanded = false }
                        ) {
                            pekerjaanOptions.forEach { option ->
                                DropdownMenuItem(
                                    text = { Text(option) },
                                    onClick = {
                                        selectedPekerjaan = option
                                        pekerjanExpanded = false
                                    }
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            val penghasilan = totalPenghasilan.toDoubleOrNull() ?: 0.0
                            val jht = iuranJHT.toDoubleOrNull() ?: 0.0
                            val bpjs = iuranBPJS.toDoubleOrNull() ?: 0.0
                            val zakat = iuranZakat.toDoubleOrNull() ?: 0.0
                            val totalPotongan = jht + bpjs + zakat

                            val biayaJabatan = if (selectedPekerjaan == "Pegawai Tetap") {
                                minOf(penghasilan * 0.005, 6_000_000.0)
                            } else 0.0

                            penghasilanNeto = if (selectedPekerjaan == "Pegawai Tetap") {
                                penghasilan - biayaJabatan - totalPotongan
                            } else {
                                penghasilan * 0.5 - totalPotongan
                            }

                            val jumlahTanggunganInt =
                                jumlahTanggungan.toIntOrNull()?.coerceAtMost(3) ?: 0
                            val ptkp = 54_000_000 + (jumlahTanggunganInt * 4_500_000)
                            val pkp = (penghasilanNeto - ptkp).coerceAtLeast(0.0)

                            penghasilanKenaPajak = pkp
                            pajak = hitungPPh(pkp)
                            hitung = true

                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF90EE90))

                    ) {
                        Text("Hitung Pajak")
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    if (hitung) {
                        Text("Penghasilan Neto: Rp ${penghasilanNeto.toInt()}")
                        Text("Penghasilan Kena Pajak: Rp ${penghasilanKenaPajak.toInt()}")
                        Text("Pajak : Rp ${pajak.toInt()}")

                        Spacer(modifier = Modifier.height(8.dp))
                    }

                    Button(
                        onClick = {
                            navController.popBackStack()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF90EE90))
                    ) {
                        Text("kembali")
                    }
                }
            }
        }
    }
}
    fun hitungPPh(pkp: Double): Double {
        var sisa = pkp
        var pajak = 0.0

        var lapisan = listOf(
            60_000_000.0 to 0.05,
            190_000_000.0 to 0.15,
            250_000_000.0 to 0.25,
            Double.MAX_VALUE to 0.30
        )

        for ((batas, tarif) in lapisan) {
            if (sisa <= 0) break
            val kena = minOf(sisa, batas)
            pajak += kena * tarif
            sisa -= kena
        }
        return pajak
    }



