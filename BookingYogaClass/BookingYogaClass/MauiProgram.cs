﻿using BookingYogaClass.Data;
using Microsoft.Extensions.Logging;
using Blazored.Toast;
using BookingYogaClass.Services;

namespace BookingYogaClass
{
    public static class MauiProgram
    {
        public static MauiApp CreateMauiApp()
        {
            var builder = MauiApp.CreateBuilder();
            builder
                .UseMauiApp<App>()
                .ConfigureFonts(fonts =>
                {
                    fonts.AddFont("OpenSans-Regular.ttf", "OpenSansRegular");
                });

            builder.Services.AddMauiBlazorWebView();
            builder.Services.AddBlazoredToast();

#if DEBUG
		builder.Services.AddBlazorWebViewDeveloperTools();
		builder.Logging.AddDebug();
#endif

            builder.Services.AddSingleton<CourseScheduleService>();

            return builder.Build();
        }
    }
}