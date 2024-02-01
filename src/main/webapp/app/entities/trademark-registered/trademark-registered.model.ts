import dayjs from 'dayjs/esm';
import { TrademarkRegisteredStatus } from 'app/entities/enumerations/trademark-registered-status.model';

export interface ITrademarkRegistered {
  id?: number;
  trademarkUUID?: string | null;
  trademarkNo?: string | null;
  year?: number | null;
  decreeNo?: string | null;
  applicantName?: string | null;
  tradeMarkOwner?: string | null;
  country?: string | null;
  nationality?: string | null;
  address?: string | null;
  applyDate?: dayjs.Dayjs | null;
  trademarkEnglish?: string | null;
  trademarkArabic?: string | null;
  category?: string | null;
  imageFileContentType?: string | null;
  imageFile?: string | null;
  imageFileUrl?: string | null;
  fileContentType?: string | null;
  file?: string | null;
  fileUrl?: string | null;
  extraFileContentType?: string | null;
  extraFile?: string | null;
  extraFileUrl?: string | null;
  publicationDate?: dayjs.Dayjs | null;
  publicationNo?: number | null;
  trademarkRegisteredStatus?: TrademarkRegisteredStatus | null;
  isHidden?: boolean | null;
  notes?: string | null;
}

export class TrademarkRegistered implements ITrademarkRegistered {
  constructor(
    public id?: number,
    public trademarkUUID?: string | null,
    public trademarkNo?: string | null,
    public year?: number | null,
    public decreeNo?: string | null,
    public applicantName?: string | null,
    public tradeMarkOwner?: string | null,
    public country?: string | null,
    public nationality?: string | null,
    public address?: string | null,
    public applyDate?: dayjs.Dayjs | null,
    public trademarkEnglish?: string | null,
    public trademarkArabic?: string | null,
    public category?: string | null,
    public imageFileContentType?: string | null,
    public imageFile?: string | null,
    public imageFileUrl?: string | null,
    public fileContentType?: string | null,
    public file?: string | null,
    public fileUrl?: string | null,
    public extraFileContentType?: string | null,
    public extraFile?: string | null,
    public extraFileUrl?: string | null,
    public publicationDate?: dayjs.Dayjs | null,
    public publicationNo?: number | null,
    public trademarkRegisteredStatus?: TrademarkRegisteredStatus | null,
    public isHidden?: boolean | null,
    public notes?: string | null
  ) {
    this.isHidden = this.isHidden ?? false;
  }
}

export function getTrademarkRegisteredIdentifier(trademarkRegistered: ITrademarkRegistered): number | undefined {
  return trademarkRegistered.id;
}
