<?php 

include("config.php");

	$nama_mahasiswa = $_POST['nama_mahasiswa'];
	$nomor_mahasiswa = $_POST['nomor_mahasiswa'];
	$alamat_mahasiswa = $_POST['alamat_mahasiswa'];

	$sql = "INSERT INTO data_mahasiswa (nama_mahasiswa, nomor_mahasiswa, alamat_mahasiswa) VALUES ('$nama_mahasiswa', '$nomor_mahasiswa', '$alamat_mahasiswa')";
	$query = mysqli_query($db, $sql);

	//apakah query update berhasil?
	if ($query) {
		
	} else {
		// kalau gagal tampilkan pesan
		die("Gagal menyimpan perubahan...");
	}
	
 ?>