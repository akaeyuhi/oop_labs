#include "framework.h"
#include "resource2.h"
#include "dowork3.h"

static WCHAR buf[256];
//static WCHAR group[256];

static INT_PTR CALLBACK Work3(HWND hDlg, UINT message, WPARAM wParam, LPARAM lParam)
{
    UNREFERENCED_PARAMETER(lParam);
    switch (message)
    {
    case WM_INITDIALOG:
        for (int i = 1; i < 6; i++)
        {
            swprintf(buf, size_t(buf), L"²Ï-0%d", i);
            SendDlgItemMessage(hDlg, IDC_GROUPLIST, LB_ADDSTRING, 0, (LPARAM)buf);
        }
        return (INT_PTR)TRUE;

    case WM_COMMAND:
        if (LOWORD(wParam) == IDOK)
        {
            LPARAM indx = SendDlgItemMessage(hDlg, IDC_GROUPLIST, LB_GETCURSEL, 0, 0);
            SendDlgItemMessage(hDlg, IDC_GROUPLIST, LB_GETTEXT, indx, (LPARAM)buf);
            EndDialog(hDlg, 1);
            return (INT_PTR)TRUE;
        }
        else if (LOWORD(wParam) == IDCANCEL)
        {
            EndDialog(hDlg, 0);
            return (INT_PTR)TRUE;
        }
        break;
    }
    return (INT_PTR)FALSE;
}

int DoWork3(HWND hWnd, HINSTANCE hInst, WCHAR* dest)
{
    DialogBox(hInst, MAKEINTRESOURCE(IDD_DIALOG1), hWnd, Work3);
    lstrcpyW(dest, buf);
    return 1;
}