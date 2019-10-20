/**












 */
 @Override
 public void onNewToken(String token) {
     Log.d(TAG, "Refreshed token: " + token);

     // If you want to send messages to this application instance or
     // manage this apps subscriptions on the server side, send the
     // Instance ID token to your app server.
     sendRegistrationToServer(token);


 }

 public void onMessageReceived(RemoteMessage remotemessage) {


     Log.d(TAG, "From: " + remoteMessage.getFrom());

     if (remoteMessage.getData().size() > 0) {
         Log.d(TAG, "Message data payload: " + remoteMessage.getData());

         if(/* Check if data needs to be processed by long runnung job */ true){
        // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
        scheduleJob();
        }
        else {
            // Handle message within 10 seconds
            handleNow();
        }

     }

     if (remoteMessage.getNotification() != null) {
        Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());

     }
 }