/private|protected|public|static|final/ && /(COLOR|FEATURES|BITRATE_MODE|Profile|Level)[^=]+=/{
  sub(/=.*/, "");
  print $NF
}
