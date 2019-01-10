package com.example.testapp.AppWidgetDemo;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.example.testapp.R;

/**
 * Implementation of App Widget functionality.
 */
public class MyAppWidget extends AppWidgetProvider {

    private static final String TAG = MyAppWidget.class.getSimpleName();

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        Log.d(TAG, "updateAppWidget: ljh appWidgetId=" + appWidgetId + "  appWidgetManager=" + appWidgetManager + "  context=" + context);

        CharSequence widgetText = context.getString(R.string.appwidget_text);
        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_app_widget);
        views.setTextViewText(R.id.appwidget_text, widgetText);

        Intent intent = new Intent(context, AppWidgetDemoActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.appwidget_text, pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Log.d(TAG, "onUpdate: ljh appWidgetIds=" + appWidgetIds + "  appWidgetManager=" + appWidgetManager + "  context=" + context);
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

    }

    @Override
    public void onEnabled(Context context) {
        Log.d(TAG, "onEnabled: ljh context=" + context);
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        Log.d(TAG, "onDisabled: ljh context=" + context);
        // Enter relevant functionality for when the last widget is disabled
    }
}

