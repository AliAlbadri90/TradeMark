import dayjs from 'dayjs/esm';

export interface ITrademarkDecree {
  id?: number;
  year?: number | null;
  decreeNo?: string | null;
  isAccepted?: boolean | null;
  decreeDate?: dayjs.Dayjs | null;
  applicantName?: string | null;
  tradeMarkOwner?: string | null;
  country?: string | null;
  applyDate?: dayjs.Dayjs | null;
  serialNo?: string | null;
  trademarkEnglish?: string | null;
  trademarkArabic?: string | null;
  category?: string | null;
  pdfFileContentType?: string | null;
  pdfFile?: string | null;
  pdfFileUrl?: string | null;
  extraPdfFileContentType?: string | null;
  extraPdfFile?: string | null;
  extraPdfFileUrl?: string | null;
  isWithdrawal?: boolean | null;
  withdrawalDecreeNo?: string | null;
  notes?: string | null;
}

export class TrademarkDecree implements ITrademarkDecree {
  constructor(
    public id?: number,
    public year?: number | null,
    public decreeNo?: string | null,
    public isAccepted?: boolean | null,
    public decreeDate?: dayjs.Dayjs | null,
    public applicantName?: string | null,
    public tradeMarkOwner?: string | null,
    public country?: string | null,
    public applyDate?: dayjs.Dayjs | null,
    public serialNo?: string | null,
    public trademarkEnglish?: string | null,
    public trademarkArabic?: string | null,
    public category?: string | null,
    public pdfFileContentType?: string | null,
    public pdfFile?: string | null,
    public pdfFileUrl?: string | null,
    public extraPdfFileContentType?: string | null,
    public extraPdfFile?: string | null,
    public extraPdfFileUrl?: string | null,
    public isWithdrawal?: boolean | null,
    public withdrawalDecreeNo?: string | null,
    public notes?: string | null
  ) {
    this.isAccepted = this.isAccepted ?? false;
    this.isWithdrawal = this.isWithdrawal ?? false;
  }
}

export function getTrademarkDecreeIdentifier(trademarkDecree: ITrademarkDecree): number | undefined {
  return trademarkDecree.id;
}
