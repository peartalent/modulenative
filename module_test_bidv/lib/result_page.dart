import 'package:flutter/material.dart';

class ResultPage extends StatelessWidget {
  final String title;

  const ResultPage({super.key, required this.title});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text("Result"),
        leading: InkWell(
          onTap: () {
            Navigator.pop(context);
          },
          child: const Icon(
              Icons.close
          ),
        ),
      ),
      body: Center(
        child: Text(title),
      ),
    );
  }
}
