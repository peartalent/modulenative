import 'dart:async';

import 'package:flutter/services.dart';

class SampleCallNativeFlutter {
  static const MethodChannel channel = MethodChannel('demo');

  static Future<String?> sendData(String key, String value) async {
    try {
      final String? rs = await channel.invokeMethod(key, value);
      return rs;
    } catch (e) {
      print(e);
      return null;
    }
  }
}
