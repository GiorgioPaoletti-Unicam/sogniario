import 'package:hive/hive.dart';
import 'package:uuid/uuid.dart';


class Utils {

  final String server = 'http://193.205.92.106:8080/';
  var box = Hive.box('data');
  var uuid = Uuid();


  Map<String, String> header = {
    'Access-Control-Allow-Headers': 'Origin, X-Requested-With, Content-Type, Accept',
    'Access-Control-Allow-Methods': 'OPTIONS, DELETE, POST, GET, PUT',
    'Access-Control-Allow-Origin': '*',
    'Content-Type': 'application/json',
    'Accept': 'application/json',
    // 'Authorization:'
  };


  String getId() {
    return box.get('id');
  }

  void setId() {
    box.put('id', uuid.v1());
  }


  String getToken() {
    return box.get('token');
  }

  void setToken(String token) {
    box.put('token', token);
  }


  bool getReminderPSQI() {
    return box.get('psqi') == null ? true : DateTime
        .now().difference(box.get('psqi'))
        .inDays > 30;
  }

  bool getReminderChronotype() {
    return box.get('chronotype') == null ? true : DateTime
        .now().difference(box.get('chronotype'))
        .inDays > 30;
  }

  void setReminderPSQI() {
    box.put('psqi', DateTime.now());
  }

  void setReminderChronotype() {
    box.put('chronotype', DateTime.now());
  }

}
